package com.pragma.onclass.configuration;

import com.pragma.onclass.adapters.driven.jpa.mysql.adapter.BootcampAdapter;
import com.pragma.onclass.adapters.driven.jpa.mysql.adapter.CapacityAdapter;
import com.pragma.onclass.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.api.ICapacityServicePort;
import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.api.usecase.BootcampUseCase;
import com.pragma.onclass.domain.api.usecase.CapacityUseCase;
import com.pragma.onclass.domain.api.usecase.TechnologyUseCase;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import com.pragma.onclass.domain.spi.ICapacityPersistencePort;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    private final ICapacityRepository capacityRepository;

    private final ICapacityEntityMapper capacityEntityMapper;


    private final IBootCampEntityMapper bootCampEntityMapper;

    private final IBootcampRepository bootCampRepository;
    @Bean
    public ITechnologyPersistencePort technologyPersistencePort(){
        return new TechnologyAdapter(technologyRepository,technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort(){return  new TechnologyUseCase(technologyPersistencePort());
    }

    @Bean
    public ICapacityPersistencePort capacityPersistencePort(){
        return new CapacityAdapter(capacityRepository,capacityEntityMapper,technologyRepository);
    }
    @Bean
    public ICapacityServicePort capacityServicePort(){
        return new CapacityUseCase(capacityPersistencePort());
    }

    @Bean
    public IBootcampPersistencePort bootCampPersistencePort(){
        return new BootcampAdapter(bootCampRepository,bootCampEntityMapper,capacityRepository);
    }

    @Bean
    public IBootcampServicePort bootCampServicePort(){
        return new BootcampUseCase(bootCampPersistencePort());
    }
}
