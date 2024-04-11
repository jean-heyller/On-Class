package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Version;

public interface IVersionPersistencePort {

    void saveVersion(Version version);


}
