package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Version;

import java.util.List;

public interface IVersionPersistencePort {

    void saveVersion(Version version);

    List<Version> getAllVersions(Integer page,
                                 Integer size,
                                 String isAscName,
                                 String isAscDate,
                                 String isAscQuota,
                                 Long bootcampId);


}
