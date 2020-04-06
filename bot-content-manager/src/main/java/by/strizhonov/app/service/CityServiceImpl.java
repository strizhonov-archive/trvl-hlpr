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
        City savedCity = repository.save(mapper.from(dtoToSave));

        return mapper.from(savedCity);
    }


    @Override
    public CityDto getById(final long dtoToGetId) {
        City foundCity = repository.findById(dtoToGetId)
                .orElseThrow(() -> {
                    LOGGER.error("City with {} id is not found.", dtoToGetId);
                    return new EntityNotFoundException(String.format("City with [%d] id is not found.", dtoToGetId));
                });

        return mapper.from(foundCity);
    }


    @Override
    public CityDto update(final CityDto dtoToUpdate) {
        long dtoToUpdateId = dtoToUpdate.getId();

        if (repository.existsById(dtoToUpdateId)) {
            City updatedCity = repository.save(mapper.from(dtoToUpdate));
            return mapper.from(updatedCity);
        } else {
            LOGGER.error("City with {} id is not found.", dtoToUpdateId);
            throw new EntityNotFoundException(String.format("City with [%d] id is not found.", dtoToUpdateId));
        }
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

}
