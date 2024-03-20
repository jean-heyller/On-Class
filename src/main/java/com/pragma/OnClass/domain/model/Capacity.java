package com.pragma.OnClass.domain.model;

import java.util.List;

public class Capacity {
    private final Long id;
    private final String name;
    private final String description;

    private List<Technology> technologies;

    public Capacity(Long id, String name, String description, List<Technology> technologies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technologies = technologies;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }
    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }




}
