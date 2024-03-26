package com.pragma.OnClass.domain.spi;

import com.pragma.OnClass.domain.model.BootCamp;

public interface IBootCampPersistencePort {
    void saveBootCamp(BootCamp bootCamp);

}
