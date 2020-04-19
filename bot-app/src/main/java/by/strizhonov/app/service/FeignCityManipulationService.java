package by.strizhonov.app.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CityClient", url = "localhost:8080/api/")
public interface FeignCityManipulationService {




}
