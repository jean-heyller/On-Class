package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;
import com.pragma.onclass.utils.exceptions.IncompatibleValueException;
import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import com.pragma.onclass.utils.exceptions.ValueAlreadyExitsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;


@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {
    public static final String VERSION_EXISTS_ERROR_MESSAGE = "The version";

    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;

    private final IBootCampEntityMapper bootCampEntityMapper;

    private final IBootcampRepository bootCampRepository;
    @Override
    public void saveVersion(Version version) {
        Optional<BootcampEntity> bootcampEntityOptional = bootCampRepository.findById(version.getBootcamp().getId());

        if(bootcampEntityOptional.isEmpty()){
            throw new NoDataFoundException();
        }

        BootcampEntity bootcampEntity = bootcampEntityOptional.get();

        VersionEntity existingVersion = versionRepository.findByStartDateAndEndDateAndMaximumQuotaAndBootcamp(
                version.getStartDate(), version.getEndDate(), version.getMaximumQuota(), bootcampEntity);

        if (existingVersion != null) {
            throw new ValueAlreadyExitsException(VERSION_EXISTS_ERROR_MESSAGE);
        }


        version.setBootcamp(bootCampEntityMapper.toModel(bootcampEntity));
        versionRepository.save(versionEntityMapper.toEntity(version));
    }



    @Override
    public List<Version> getAllVersions(Integer page, Integer size, String isAscName, String isAscDate, String isAscQuota, Long bootcampId) {
        if(page < 0 || size < 0){
            throw new IncompatibleValueException();
        }

        if (isAscName == null && isAscDate == null && isAscQuota == null && bootcampId == null) {
            Pageable pageable = PageRequest.of(page, size);
            List<VersionEntity> allVersions = versionRepository.findAll(pageable).getContent();
            return versionEntityMapper.toModelList(allVersions);
        }

        Sort sort = Sort.unsorted();
        if (("asc".equals(isAscName) || "desc".equals(isAscName)) && bootcampId == null) {
            sort = Sort.by("bootcamp.name");
        }
        if ("asc".equals(isAscDate) || "desc".equals(isAscDate)) {
            sort = Sort.by("startDate");
        } else if ("asc".equals(isAscQuota) || "desc".equals(isAscQuota)) {
            sort = Sort.by("maximumQuota");
        }

        if ("desc".equals(isAscName) || "desc".equals(isAscDate) || "desc".equals(isAscQuota)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        List<VersionEntity> allVersions = versionRepository.findAll(pageable).getContent();

        if (bootcampId != null) {
            allVersions = allVersions.stream()
                    .filter(versionEntity -> versionEntity.getBootcamp().getId().equals(bootcampId))
                    .toList();
        }

        return versionEntityMapper.toModelList(allVersions);
    }


}

