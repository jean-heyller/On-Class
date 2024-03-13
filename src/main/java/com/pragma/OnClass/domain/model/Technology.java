package com.pragma.OnClass.domain.model;

import com.pragma.OnClass.domain.exception.EmptyFieldException;
import com.pragma.OnClass.domain.exception.NegativeNotAllowedException;
import com.pragma.OnClass.domain.util.DomainConstants;
import static java.util.Objects.requireNonNull;

public class Technology {
    private final Long id;
    private final String name;

    private final String description;

    public Technology(Long id, String name, String description) {
        if(name.trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if(name.length()>50){
            throw new NegativeNotAllowedException(DomainConstants.Field.NAME.toString());
        }
        if(description.length() > 90){
            throw new NegativeNotAllowedException(DomainConstants.Field.DESCRIPTION.toString());
        }
        this.id = id;
        this.name =  requireNonNull(name,DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_MESSAGE);
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




}
