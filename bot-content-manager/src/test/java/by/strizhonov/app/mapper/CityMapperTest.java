package by.strizhonov.app.mapper;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class CityMapperTest {


    private final CityMapper mapper = CityMapper.INSTANCE;


    @Test
    public void shouldCreateSameDto() {
        long id = 1;
        String name = "TEST_NAME_1";
        String description = "TEST_DESCRIPTION_1";
        City cityToConvert = City.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
        CityDto convertedCity = mapper.fromEntity(cityToConvert);

        Assert.assertEquals(name, convertedCity.getName());
        Assert.assertEquals(id, convertedCity.getId());
        Assert.assertEquals(description, convertedCity.getDescription());
    }


    @Test
    public void shouldCreateSameEntity() {
        long id = 2;
        String name = "TEST_NAME_2";
        String description = "TEST_DESCRIPTION_2";
        CityDto dtoToTest = CityDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
        City converted = mapper.fromDto(dtoToTest);

        Assert.assertEquals(name, converted.getName());
        Assert.assertEquals(id, converted.getId());
        Assert.assertEquals(description, converted.getDescription());
    }


    @Test
    public void shouldCreateListOfSameDtos() {
        List<City> manuallyCreatedCities = createCities();
        List<CityDto> convertedCities = mapper.allFromEntities(manuallyCreatedCities);

        for (City current : manuallyCreatedCities) {
            boolean containsSameCity = convertedCities
                    .contains(CityDto.builder()
                            .id(current.getId())
                            .name(current.getName())
                            .description(current.getDescription())
                            .build());
            Assert.assertTrue(containsSameCity);
        }

    }


    private List<City> createCities() {
        List<City> result = new ArrayList<>();

        result.add(City.builder()
                .id(3)
                .name("TEST_NAME_3")
                .description("TEST_DESCRIPTION_3")
                .build());

        result.add(City.builder()
                .id(4)
                .name("TEST_NAME_4")
                .description("TEST_DESCRIPTION_4")
                .build());

        result.add(City.builder()
                .id(5)
                .name("TEST_NAME_5")
                .description("TEST_DESCRIPTION_5")
                .build());

        return result;
    }

}
