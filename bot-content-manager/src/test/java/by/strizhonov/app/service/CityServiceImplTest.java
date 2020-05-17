package by.strizhonov.app.service;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;
import by.strizhonov.app.repository.CityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceImplTest {

    @Mock
    private CityRepository mockRepository;

    @InjectMocks
    private CityServiceImpl serviceToTest;


    @Test
    public void shouldCallRepositorySave() {
        CityDto cityToCreate = CityDto.builder()
                .id(1)
                .name("TEST_NAME")
                .description("TEST_DESCRIPTION")
                .build();

        serviceToTest.create(cityToCreate);
        Mockito.verify(mockRepository, Mockito.atLeast(1)).save(Mockito.any(City.class));
    }


    @Test
    public void shouldCallRepositoryGet() {
        Mockito.when(mockRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(City.builder()
                        .id(2)
                        .name("TEST_NAME_2")
                        .description("TEST_DESCRIPTION_2")
                        .build()));

        serviceToTest.getById(2);
        Mockito.verify(mockRepository, Mockito.atLeast(1)).findById(Mockito.any(Long.class));
    }


    @Test
    public void shouldCallRepositoryUpdate() {
        CityDto cityToUpdate = CityDto.builder()
                .id(3)
                .name("TEST_NAME_3")
                .description("TEST_DESCRIPTION_3")
                .build();
        Mockito.when(mockRepository.existsById(Mockito.any(Long.class))).thenReturn(true);

        serviceToTest.update(cityToUpdate);
        Mockito.verify(mockRepository, Mockito.atLeast(1)).saveAndFlush(Mockito.any(City.class));
    }


    @Test
    public void shouldCallRepositoryDelete() {
        City cityToDelete = City.builder()
                .id(4)
                .name("TEST_NAME_4")
                .description("TEST_DESCRIPTION_4")
                .build();
        Mockito.when(mockRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(cityToDelete));

        serviceToTest.delete(4);
        Mockito.verify(mockRepository, Mockito.atLeast(1)).delete(Mockito.any(City.class));
    }


    @Test
    public void shouldCallRepositoryFindAll() {
        serviceToTest.findAll();
        Mockito.verify(mockRepository, Mockito.atLeast(1)).findAll();
    }


    @SuppressWarnings("all")
    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenCreateNull() {
        serviceToTest.create(null);
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenGetNonExisted() {
        Mockito.when(mockRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        serviceToTest.getById(55);
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenUpdateNull() {
        serviceToTest.update(null);
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenDeleteNonExisted() {
        Mockito.when(mockRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        serviceToTest.delete(22);
    }

}