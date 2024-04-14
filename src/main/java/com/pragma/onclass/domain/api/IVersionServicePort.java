package com.pragma.onclass.domain.api;

import com.pragma.onclass.domain.model.Version;

import java.util.List;

public interface IVersionServicePort {
    void saveVersion(Version version);

    List<Version> getAllVersions(Integer page,
                                 Integer size,
                                 String isAscName,
                                 String isAscDate,
                                 String isAscQuota,
                                 Long bootcampId);



}
