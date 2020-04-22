package by.strizhonov.app.service;

import by.strizhonov.app.dto.CityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CityClient", url = "localhost:8080/api")
public interface FeignCityManipulationService {


    @RequestMapping(method = RequestMethod.GET, value = "/city/name/{name}", consumes = "application/json")
    CityDto findByName(@PathVariable("name") String cityToSearch);

}
