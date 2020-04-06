package by.strizhonov.app.controller;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CityControllerTest {


    @Mock
    private CityService mockService;

    @InjectMocks
    private CityController controllerToTest;


    @Test
    public void shouldCallServiceCreate() {
        CityDto cityToCreate = new CityDto(1, "TEST_NAME_1", "TEST_DESCRIPTION_1");

        controllerToTest.save(cityToCreate);
        Mockito.verify(mockService, Mockito.atLeast(1)).create(Mockito.any(CityDto.class));
    }


    @Test
    public void shouldCallServiceGet() {
        Mockito.when(mockService.getById(Mockito.any(Long.class)))
                .thenReturn(new CityDto(2, "TEST_NAME_2", "TEST_DESCRIPTION_2"));

        controllerToTest.get(2);
        Mockito.verify(mockService, Mockito.atLeast(1)).getById(Mockito.any(Long.class));
    }


    @Test
    public void shouldCallServiceUpdate() {
        CityDto cityToUpdate = new CityDto(3, "TEST_NAME_3", "TEST_DESCRIPTION_3");
        Mockito.when(mockService.update(Mockito.any(CityDto.class))).thenReturn(cityToUpdate);

        controllerToTest.update(cityToUpdate);
        Mockito.verify(mockService, Mockito.atLeast(1)).update(Mockito.any(CityDto.class));
    }


    @Test
    public void shouldCallServiceDelete() {
        controllerToTest.delete(4);
        Mockito.verify(mockService, Mockito.atLeast(1)).delete(Mockito.any(Long.class));
    }


    @Test
    public void shouldCallServiceFindAll() {
        controllerToTest.findAll();
        Mockito.verify(mockService, Mockito.atLeast(1)).findAll();
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenSaveNull() {
        controllerToTest.save(null);
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenUpdateNull() {
        controllerToTest.update(null);
    }

}
