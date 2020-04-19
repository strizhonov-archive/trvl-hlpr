package by.strizhonov.app.mapper;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(JUnit4.class)
public class CityMapperTest {

    @Test
    public void shouldConvertValidEntity() {
        City toConvert = City.builder()
                .id(1)
                .name("TEST_NAME")
                .description("DESC")
                .build();

        CityDto converted = CityMapper.INSTANCE.fromEntity(toConvert);

        Assert.assertEquals(1, converted.getId());
        Assert.assertEquals("TEST_NAME", converted.getName());
        Assert.assertEquals("DESC", converted.getDescription());
    }


    @Test
    public void shouldConvertAllToDto() {
        City toConvert = City.builder()
                .id(1)
                .name("TEST_NAME")
                .description("DESC")
                .build();

        List<CityDto> converted = CityMapper.INSTANCE.allFromEntities(Collections.singletonList(toConvert));

        Assert.assertEquals(1, converted.size());
        Assert.assertEquals(1, converted.get(0).getId());
        Assert.assertEquals("TEST_NAME", converted.get(0).getName());
        Assert.assertEquals("DESC", converted.get(0).getDescription());
    }

}
