package ch.heArc.hotelDiscovery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.heArc.hotelDiscovery.models.Hotel;
import ch.heArc.hotelDiscovery.models.User;



public interface IHotelRepository extends JpaRepository<Hotel, Integer> {

}
