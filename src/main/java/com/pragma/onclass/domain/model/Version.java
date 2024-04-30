package com.pragma.onclass.domain.model;

import java.time.LocalDate;
import java.util.Date;

public class Version {

    private final Long id;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final Integer maximumQuota;

    public void setBootcamp(Bootcamp bootcamp) {
        this.bootcamp = bootcamp;
    }

    private  Bootcamp bootcamp;

    public Bootcamp getBootcamp() {
        return bootcamp;
    }

    public Version(Long id, LocalDate startDate, LocalDate endDate, Integer maximumQuota, Bootcamp bootcamp) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maximumQuota = maximumQuota;
        this.bootcamp = bootcamp;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getMaximumQuota() {
        return maximumQuota;
    }










}
