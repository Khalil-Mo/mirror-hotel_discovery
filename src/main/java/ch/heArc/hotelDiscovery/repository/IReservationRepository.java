package ch.heArc.hotelDiscovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.heArc.hotelDiscovery.models.Hotel;
import ch.heArc.hotelDiscovery.models.Reservation;
import ch.heArc.hotelDiscovery.models.Room;
import ch.heArc.hotelDiscovery.models.User;



public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
	//List<Reservation> findByHotel(Hotel hotel);
	List<Reservation> findByUser(User user);
	
	@Query("SELECT re FROM Reservation re JOIN re.room ro JOIN ro.hotel ho WHERE ho.manager = ?1")
	List<Reservation> findByHotelier(User hotelier);
	
}
