package by.strizhonov.app.mapper;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapperImpl implements ModelMapper {


    public City fromDto(final CityDto dtoFrom) {
        City entityTo = new City();
        BeanUtils.copyProperties(dtoFrom, entityTo);
        return entityTo;
    }


    public CityDto fromEntity(final City entityFrom) {
        CityDto dtoTo = new CityDto();
        BeanUtils.copyProperties(entityFrom, dtoTo);
        return dtoTo;
    }


    public List<CityDto> fromEntities(final List<City> entitiesFrom) {
        List<CityDto> result = new ArrayList<>();
        for (City current : entitiesFrom) {
            result.add(fromEntity(current));
        }
        return result;
    }


    private ModelMapperImpl() {
        // Util class private constructor
    }

}
