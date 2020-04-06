package by.strizhonov.app.util;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class EntityDtoMapperTest {


    @Test
    public void shouldCreateSameDto() {
        long id = 1;
        String name = "TEST_NAME_1";
        String description = "TEST_DESCRIPTION_1";
        City cityToConvert = new City(id, name, description);
        CityDto convertedCity = new EntityDtoMapper().fromDto(cityToConvert);

        Assert.assertEquals(name, convertedCity.getName());
        Assert.assertEquals(id, convertedCity.getId());
        Assert.assertEquals(description, convertedCity.getDescription());
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenEntityIsNull() {
        new EntityDtoMapper().fromDto((City) null);
    }


    @Test
    public void shouldCreateSameEntity() {
        long id = 2;
        String name = "TEST_NAME_2";
        String description = "TEST_DESCRIPTION_2";
        CityDto dtoToTest = new CityDto(id, name, description);
        City converted = new EntityDtoMapper().fromDto(dtoToTest);

        Assert.assertEquals(name, converted.getName());
        Assert.assertEquals(id, converted.getId());
        Assert.assertEquals(description, converted.getDescription());
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenDtoIsNull() {
        new EntityDtoMapper().fromDto((CityDto) null);
    }


    @Test
    public void shouldCreateListOfSameDtos() {
        List<City> manuallyCreatedCities = createCities();
        List<CityDto> convertedCities = new EntityDtoMapper().allFrom(manuallyCreatedCities);

        for (City current : manuallyCreatedCities) {
            boolean containsSameCity = convertedCities
                    .contains(new CityDto(current.getId(), current.getName(), current.getDescription()));
            Assert.assertTrue(containsSameCity);
        }

    }


    @SuppressWarnings("all")
    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenEntitiesAreNull() {
        new EntityDtoMapper().allFrom(null);
    }


    private List<City> createCities() {
        List<City> result = new ArrayList<>();

        long id = 3;
        String name = "TEST_NAME_3";
        String description = "TEST_DESCRIPTION_3";
        result.add(new City(id, name, description));

        id = 4;
        name = "TEST_NAME_4";
        description = "TEST_DESCRIPTION_4";
        result.add(new City(id, name, description));

        id = 5;
        name = "TEST_NAME_5";
        description = "TEST_DESCRIPTION_5";
        result.add(new City(id, name, description));

        return result;
    }
}
