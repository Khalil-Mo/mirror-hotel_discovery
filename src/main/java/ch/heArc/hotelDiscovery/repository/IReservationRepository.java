package ch.heArc.hotelDiscovery.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.heArc.hotelDiscovery.models.Reservation;
import ch.heArc.hotelDiscovery.models.User;



public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
	//List<Reservation> findByHotel(Hotel hotel);
	
	//List<Reservation> findByUserByOrderByDateStartAsc(User user);
	List<Reservation> findByUserAndDateEndBeforeOrderByDateStartAsc(User user, Date date);
	List<Reservation> findByUserAndDateEndAfterOrderByDateStartAsc(User user, Date date);
	
	
	@Query("SELECT re FROM Reservation re JOIN re.room ro JOIN ro.hotel ho WHERE ho.manager = ?1 AND re.dateEnd <= ?2 ORDER BY re.dateStart")
	List<Reservation> findByHotelierBeforeToday(User hotelier, Date date);
	
	@Query("SELECT re FROM Reservation re JOIN re.room ro JOIN ro.hotel ho WHERE ho.manager = ?1 AND re.dateEnd >= ?2 ORDER BY re.dateStart")
	List<Reservation> findByHotelierAfterToday(User hotelier, Date date);
	
}
