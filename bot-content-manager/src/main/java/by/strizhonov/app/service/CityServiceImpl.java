package by.strizhonov.app.service;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.mapper.CityMapper;
import by.strizhonov.app.model.City;
import by.strizhonov.app.repository.CityRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
    private static final String NOT_FOUND_MESSAGE_FOR_LOGGER = "City with {} id is not found.";
    private static final String ALREADY_EXISTS_MESSAGE_FOR_LOGGER = "City with [{}] name does already exist.";
    private static final String NOT_FOUND_MESSAGE_FOR_EXCEPTION = "City with [%d] id is not found.";
    private static final String NOT_FOUND_BY_NAME_MESSAGE_FOR_EXCEPTION = "City with [%s] id is not found.";
    private static final String ALREADY_EXISTS_MESSAGE_FOR_EXCEPTION = "City with [%s] name does already exist.";

    private final CityMapper mapper = CityMapper.INSTANCE;

    private final CityRepository repository;


    @Override
    public CityDto create(final CityDto dtoToSave) {
        String cityToCreateName = dtoToSave.getName();
        if (nameIsAvailable(cityToCreateName)) {
            return performSaving(dtoToSave);
        } else {
            LOGGER.error(ALREADY_EXISTS_MESSAGE_FOR_LOGGER, cityToCreateName);
            throw new EntityExistsException(String.format(ALREADY_EXISTS_MESSAGE_FOR_EXCEPTION, cityToCreateName));
        }

    }


    @Override
    public CityDto getById(final long dtoToGetId) {
        City foundCity = repository.findById(dtoToGetId)
                .orElseThrow(() -> {
                    LOGGER.error(NOT_FOUND_MESSAGE_FOR_LOGGER, dtoToGetId);
                    return new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE_FOR_EXCEPTION, dtoToGetId));
                });

        return mapper.fromEntity(foundCity);
    }


    @Override
    public CityDto update(final CityDto dtoToUpdate) {
        checkForUpdateValidity(dtoToUpdate);
        City toUpdate = mapper.fromDto(dtoToUpdate);
        repository.saveAndFlush(toUpdate);
        return mapper.fromEntity(toUpdate);
    }


    @Override
    public void delete(final long dtoToDeleteId) {
        City foundCity = repository.findById(dtoToDeleteId)
                .orElseThrow(() -> {
                    LOGGER.error(NOT_FOUND_MESSAGE_FOR_LOGGER, dtoToDeleteId);
                    return new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE_FOR_EXCEPTION, dtoToDeleteId));
                });

        repository.delete(foundCity);
    }


    @Override
    public List<CityDto> findAll() {
        List<City> allCities = repository.findAll();
        return mapper.allFromEntities(allCities);
    }

    @Override
    public CityDto getByName(final String name) {
        City foundCity = repository.findByName(name)
                .orElseThrow(() -> {
                    LOGGER.error(NOT_FOUND_MESSAGE_FOR_LOGGER, name);
                    return new EntityNotFoundException(String.format(NOT_FOUND_BY_NAME_MESSAGE_FOR_EXCEPTION, name));
                });

        return mapper.fromEntity(foundCity);
    }


    private boolean nameIsAvailable(final String cityName) {
        return !repository.findByName(cityName).isPresent();
    }


    private CityDto performSaving(final CityDto dtoToSave) {
        ignoreId(dtoToSave);
        City entityToSave = mapper.fromDto(dtoToSave);
        repository.save(entityToSave);
        return mapper.fromEntity(entityToSave);
    }


    private void checkForUpdateValidity(final CityDto dtoToUpdate) {
        long dtoToUpdateId = dtoToUpdate.getId();
        if (!repository.existsById(dtoToUpdateId)) {
            LOGGER.error(NOT_FOUND_MESSAGE_FOR_LOGGER, dtoToUpdateId);
            throw new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE_FOR_EXCEPTION, dtoToUpdateId));
        }

        String cityName = dtoToUpdate.getName();
        if (!nameIsAvailable(cityName)) {
            LOGGER.error(ALREADY_EXISTS_MESSAGE_FOR_LOGGER, cityName);
            throw new EntityExistsException(String.format(ALREADY_EXISTS_MESSAGE_FOR_EXCEPTION, cityName));
        }
    }


    private void ignoreId(final CityDto dtoToSave) {
        dtoToSave.setId(0);
    }

}
