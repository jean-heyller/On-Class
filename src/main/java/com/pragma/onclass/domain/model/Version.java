package com.pragma.onclass.domain.model;

import java.util.Date;

public class Version {

    private final Long id;

    private final Date startDate;

    private final Date endDate;

    private final Integer maximumQuota;

    public void setBootcamp(Bootcamp bootcamp) {
        this.bootcamp = bootcamp;
    }

    private  Bootcamp bootcamp;

    public Bootcamp getBootcamp() {
        return bootcamp;
    }

    public Version(Long id, Date startDate, Date endDate, Integer maximumQuota, Bootcamp bootcamp) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maximumQuota = maximumQuota;
        this.bootcamp = bootcamp;
    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getMaximumQuota() {
        return maximumQuota;
    }










}
