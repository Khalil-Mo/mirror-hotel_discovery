package ch.heArc.hotelDiscovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.heArc.hotelDiscovery.models.Hotel;
import ch.heArc.hotelDiscovery.models.Room;



public interface IRoomRepository extends JpaRepository<Room, Integer> {
	List<Room> findByHotel(Hotel hotel);
}
