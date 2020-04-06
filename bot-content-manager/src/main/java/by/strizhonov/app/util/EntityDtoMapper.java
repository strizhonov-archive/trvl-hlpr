package by.strizhonov.app.util;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityDtoMapper {

    public City from(final CityDto dtoFrom) {
        City entityTo = new City();
        BeanUtils.copyProperties(dtoFrom, entityTo);
        return entityTo;
    }

    public CityDto from(final City entityFrom) {
        CityDto dtoTo = new CityDto();
        BeanUtils.copyProperties(entityFrom, dtoTo);
        return dtoTo;
    }

    public List<CityDto> allFrom(final List<City> entitiesFrom) {
        List<CityDto> result = new ArrayList<>();
        for (City current : entitiesFrom) {
            result.add(from(current));
        }
        return result;
    }

}
