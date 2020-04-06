package by.strizhonov.app.service;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.model.City;
import by.strizhonov.app.repository.CityRepository;
import by.strizhonov.app.util.EntityDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private EntityDtoMapper mapper;

    @Autowired
    private CityRepository repository;


    @Override
    public CityDto create(final CityDto dtoToSave) {
        String cityToCreateName = dtoToSave.getName();
        if (nameIsAvailable(cityToCreateName)) {
            return performSaving(dtoToSave);
        } else {
            LOGGER.error("City with [{}] name does already exist.", cityToCreateName);
            throw new EntityExistsException(String.format("City with [%s] name does already exist.", cityToCreateName));
        }

    }


    @Override
    public CityDto getById(final long dtoToGetId) {
        City foundCity = repository.findById(dtoToGetId)
                .orElseThrow(() -> {
                    LOGGER.error("City with {} id is not found.", dtoToGetId);
                    return new EntityNotFoundException(String.format("City with [%d] id is not found.", dtoToGetId));
                });

        return mapper.fromEntity(foundCity);
    }


    @Override
    public CityDto update(final CityDto dtoToUpdate) {
        checkForUpdateValidity(dtoToUpdate);

        City updatedCity = repository.save(mapper.fromDto(dtoToUpdate));
        return mapper.fromEntity(updatedCity);
    }


    @Override
    public void delete(final long dtoToDeleteId) {
        City foundCity = repository.findById(dtoToDeleteId)
                .orElseThrow(() -> {
                    LOGGER.error("City with {} id is not found.", dtoToDeleteId);
                    return new EntityNotFoundException(String.format("City with [%d] id is not found.", dtoToDeleteId));
                });

        repository.delete(foundCity);
    }


    @Override
    public List<CityDto> findAll() {
        List<City> allCities = repository.findAll();
        return mapper.allFrom(allCities);
    }


    private boolean nameIsAvailable(final String cityName) {
        return repository.findByName(cityName) == null;
    }


    private CityDto performSaving(final CityDto dtoToSave) {
        ignoreId(dtoToSave);
        City entityToSave = mapper.fromDto(dtoToSave);
        City savedCity = repository.save(entityToSave);
        return mapper.fromEntity(savedCity);
    }


    private void checkForUpdateValidity(final CityDto dtoToUpdate) {
        long dtoToUpdateId = dtoToUpdate.getId();
        if (!repository.existsById(dtoToUpdateId)) {
            LOGGER.error("City with {} id is not found.", dtoToUpdateId);
            throw new EntityNotFoundException(String.format("City with [%d] id is not found.", dtoToUpdateId));
        }

        String cityName = dtoToUpdate.getName();
        if (!nameIsAvailable(cityName)) {
            LOGGER.error("City with [{}] name does already exist.", cityName);
            throw new EntityExistsException(String.format("City with [%s] name does already exist.", cityName));
        }
    }


    private void ignoreId(final CityDto dtoToSave) {
        dtoToSave.setId(0);
    }


}
