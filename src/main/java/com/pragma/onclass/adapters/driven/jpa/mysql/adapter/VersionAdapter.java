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

        Map<String, String> sortParameters = new HashMap<>();
        sortParameters.put("bootcamp.name", isAscName);
        sortParameters.put("startDate", isAscDate);
        sortParameters.put("maximumQuota", isAscQuota);

        List<Sort.Order> orders = new ArrayList<>();
        for (Map.Entry<String, String> entry : sortParameters.entrySet()) {
            if ("asc".equals(entry.getValue())) {
                orders.add(new Sort.Order(Sort.Direction.ASC, entry.getKey()));
            } else if ("desc".equals(entry.getValue())) {
                orders.add(new Sort.Order(Sort.Direction.DESC, entry.getKey()));
            }
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<VersionEntity> allVersions = bootcampId != null ? versionRepository.findByBootcamp_Id(bootcampId, pageable).getContent() : versionRepository.findAll(pageable).getContent();

        return versionEntityMapper.toModelList(allVersions);
    }
}

