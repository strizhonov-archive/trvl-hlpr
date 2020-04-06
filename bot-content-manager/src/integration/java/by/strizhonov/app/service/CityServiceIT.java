package by.strizhonov.app.service;

import by.strizhonov.app.dto.CityDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CityServiceIT {


    @Autowired
    private CityService serviceToTest;


    @Test
    public void shouldSaveCity() {
        int citiesNumberBeforeSaving = serviceToTest.findAll().size();
        CityDto cityToSave = new CityDto(0, "TEST_NAME_1", "TEST_DESCRIPTION_1");
        CityDto savedCity = serviceToTest.create(cityToSave);
        int citiesNumberAfterSaving = serviceToTest.findAll().size();
        Assert.assertEquals(citiesNumberBeforeSaving + 1, citiesNumberAfterSaving);
        Assert.assertTrue(savedCity.getId() > 0);
    }


    @Test
    public void shouldGetCity() {
        CityDto cityToSave = new CityDto(0, "TEST_NAME_2", "TEST_DESCRIPTION_2");
        CityDto savedCity = serviceToTest.create(cityToSave);
        CityDto retrievedCity = serviceToTest.getById(savedCity.getId());
        Assert.assertNotNull(retrievedCity);
        Assert.assertEquals("TEST_NAME_2", retrievedCity.getName());
    }


    @Test
    public void shouldDeleteCity() {
        CityDto cityToSave = new CityDto(0, "TEST_NAME_3", "TEST_DESCRIPTION_3");
        CityDto savedCity = serviceToTest.create(cityToSave);
        int citiesNumberBeforeDeleting = serviceToTest.findAll().size();
        serviceToTest.delete(savedCity.getId());
        int citiesNumberAfterDeleting = serviceToTest.findAll().size();
        Assert.assertEquals(citiesNumberBeforeDeleting - 1, citiesNumberAfterDeleting);
    }


    @Test
    public void shouldUpdateCity() {
        CityDto cityToSave = new CityDto(0, "TEST_NAME_4", "TEST_DESCRIPTION_4");
        CityDto savedCity = serviceToTest.create(cityToSave);
        savedCity.setName("UPDATED_TEST_NAME");
        CityDto updatedCity = serviceToTest.update(savedCity);
        Assert.assertEquals("UPDATED_TEST_NAME", updatedCity.getName());
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenSavingExistingName() {
        CityDto cityToSave = new CityDto(0, "TEST_NAME_4", "TEST_DESCRIPTION_4");
        serviceToTest.create(cityToSave);
        CityDto anotherCityWithTheSameName = new CityDto(0, "TEST_NAME_4", "TEST_DESCRIPTION_5");
        serviceToTest.create(anotherCityWithTheSameName);
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenUpdatingWithExistingName() {
        CityDto cityToSave = new CityDto(1, "TEST_NAME_4", "TEST_DESCRIPTION_4");
        serviceToTest.create(cityToSave);
        CityDto anotherCityToSave = new CityDto(2, "TEST_NAME_5", "TEST_DESCRIPTION_5");
        CityDto cityToUpdate = serviceToTest.getById(2);
        cityToUpdate.setName("TEST_NAME_4");
        serviceToTest.update(cityToUpdate);
    }

}
