package by.strizhonov.app.mapper;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;

import java.util.List;

public interface ModelMapper {

    City fromDto(final CityDto dtoFrom);

    CityDto fromEntity(final City entityFrom);

    List<CityDto> fromEntities(final List<City> entitiesFrom);

}
