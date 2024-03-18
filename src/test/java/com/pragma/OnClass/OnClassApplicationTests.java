package com.pragma.OnClass;

import com.pragma.OnClass.domain.api.ITechnologyServicePort;
import com.pragma.OnClass.domain.api.usecase.TechnologyUseCase;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;



class OnClassApplicationTests {


	@Mock
	private ITechnologyPersistencePort technologyPersistencePort;
	private ITechnologyServicePort technologyServicePort;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		technologyServicePort = new TechnologyUseCase(technologyPersistencePort);
	}
	@Test
	void testSaveTechnology() {
		// Arrange
		Technology technology = new Technology(6L, "Django", "Programing Language");
		// Act
		technologyServicePort.saveTechnology(technology);
		// Assert
		verify(technologyPersistencePort, times(1)).saveTechnology(technology);
	}

}
