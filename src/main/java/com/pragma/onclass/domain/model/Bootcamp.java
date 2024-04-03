package com.pragma.onclass.domain.model;

import java.util.List;

public class Bootcamp {
    private final Long id;
    private  String name;

    public void setName(String name) {
        this.name = name;
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

    public List<Capacity> getCapacities() {
        return capacities;
    }

    private final String description;
    List<Capacity> capacities;

    public Bootcamp(Long id, String name, String description, List<Capacity> capacities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacities = capacities;
    }



}
