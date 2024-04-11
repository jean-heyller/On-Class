package com.pragma.onclass.adapters.driving.http.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class AddVersionRequest {

    private final Date startDate;


    private final Date endDate;


    private final Integer maximumQuota;


    private final Long bootcampId;




}
