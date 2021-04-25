package ch.heArc.hotelDiscovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.heArc.hotelDiscovery.models.Hotel;
import ch.heArc.hotelDiscovery.models.User;



public interface IHotelRepository extends JpaRepository<Hotel, Integer> {
	List<Hotel> findByManager(User manager);
	List<Hotel> findByCity(String city);
	
	@Query("SELECT DISTINCT ho.city FROM Hotel ho")
    List<String> findDistinctCity();
	
}
