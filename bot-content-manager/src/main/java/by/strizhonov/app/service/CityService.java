package by.strizhonov.app.service;

import by.strizhonov.app.dto.CityDto;

import java.util.List;

public interface CityService {

    CityDto create(CityDto dtoToSave);

    CityDto getById(long dtoToGetId);

    CityDto update(CityDto dtoToUpdate);

    void delete(long dtoToDeleteId);

    List<CityDto> findAll();

    CityDto getByName(String name);
}
