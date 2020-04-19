package by.strizhonov.app.mapper;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    City fromDto(CityDto dto);

    CityDto fromEntity(City entity);

    @IterableMapping(elementTargetType = CityDto.class)
    List<CityDto> allFromEntities(List<City> entities);

}
