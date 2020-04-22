package by.strizhonov.app.controller;

import by.strizhonov.app.dto.CityDto;
import by.strizhonov.app.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/city")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CityController {

    private final CityService service;


    @PostMapping
    public CityDto save(@RequestBody final CityDto dtoToSave) {
        return service.create(Objects.requireNonNull(dtoToSave));
    }


    @GetMapping(value = "/{id}")
    public CityDto get(@PathVariable("id") final long dtoToGetId) {
        return service.getById(dtoToGetId);
    }


    @PutMapping
    public CityDto update(@RequestBody final CityDto dtoToUpdate) {
        return service.update(Objects.requireNonNull(dtoToUpdate));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final long dtoToDeleteId) {
        service.delete(dtoToDeleteId);
    }


    @GetMapping
    public List<CityDto> findAll() {
        return service.findAll();
    }


    @GetMapping(value = "/name/{name}")
    public CityDto getByName(@PathVariable("name") final String name) {
        return service.getByName(name);
    }
}
