package by.strizhonov.app.dao;

import by.strizhonov.app.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, Long> {

    @Query(value = "SELECT * FROM CITY WHERE NAME = ?1", nativeQuery = true)
    City searchByName(String cityToSearch);

}
