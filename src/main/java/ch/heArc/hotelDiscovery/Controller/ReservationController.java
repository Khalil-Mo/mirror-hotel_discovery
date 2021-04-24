package ch.heArc.hotelDiscovery.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import ch.heArc.hotelDiscovery.models.Hotel;
import ch.heArc.hotelDiscovery.models.Reservation;
import ch.heArc.hotelDiscovery.models.Room;
import ch.heArc.hotelDiscovery.models.User;
import ch.heArc.hotelDiscovery.repository.IHotelRepository;
import ch.heArc.hotelDiscovery.repository.IReservationRepository;
import ch.heArc.hotelDiscovery.services.HotelService;

@Controller
public class ReservationController {

	@Autowired
	IHotelRepository hotelRepository;

	@Autowired
	HotelService hotelService;

	@Autowired
	RoomController roomController;

	@Autowired
	IReservationRepository reservationRepository;

	@GetMapping("/view/{hotelId}")
	public String searchCity(Map<String, Object> model, @PathVariable int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);

		if (hotel.isPresent()) {
			List<Room> rooms = roomController.roomRepository.findByHotel(hotel.get());
			model.put("hotel", hotel.get());
			model.put("rooms", rooms);
			model.put("reservation", new Reservation());

			return "reservation/viewHotel";
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}

	@PostMapping("/client/book")
	public String create(Reservation reservation) {
		Object connectedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (connectedUser instanceof UserDetails) {
			User user = (User) (connectedUser);
			reservation.setUser(user);
			reservationRepository.save(reservation);
			return "redirect:/bookings";
		}
		return "redirect:/login";
	}

	// For the client
	@GetMapping("/client/bookings")
	public String myBooking(Map<String, Object> model) {
		Object connectedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (connectedUser instanceof UserDetails) {
			User user = (User) (connectedUser);
			List<Reservation> reservations = reservationRepository.findByUser(user);
			model.put("reservations", reservations);
			return "reservation/bookings";
		}
		return "redirect:/login";
	}

}
