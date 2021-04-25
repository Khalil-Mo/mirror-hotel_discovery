package ch.heArc.hotelDiscovery.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
	public String viewHotel(Map<String, Object> model, @PathVariable int hotelId) {

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
	
	@GetMapping("/discover/{hotelId}")
	public String discoverHotel(Map<String, Object> model, @PathVariable int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);

		if (hotel.isPresent()) {
			List<Room> rooms = roomController.roomRepository.findByHotel(hotel.get());
			model.put("hotel", hotel.get());
			model.put("rooms", rooms);
			model.put("reservation", new Reservation());
			model.put("discover", true);

			return "reservation/viewHotel";
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}

	@PostMapping("/client/book")
	public String create(Reservation reservation, HttpServletRequest request) {
		Object connectedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (connectedUser instanceof UserDetails) {
			User user = (User) (connectedUser);
			reservation.setUser(user);
			reservationRepository.save(reservation);
			System.out.println("Discovery::::");
			System.out.println("Discovery::::");
			System.out.println("Discovery::::");
			System.out.println(request.getParameter("discovery"));
			if (request.getParameter("discovery") != null && request.getParameter("discovery").equals("discover")) {
				System.out.println("discovery inside");
				return "redirect:/client/discovery";
			}
			return "redirect:/client/bookings";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/client/discovery")
	public String discovery(Map<String, Object> model) {
		
		
		List<Hotel> allHotels = hotelRepository.findAll();
		Random r = new Random();
		int n = r.nextInt(allHotels.size());
		int idHotel = allHotels.get(n).getIdHotel();
		return "redirect:/discover/" + idHotel;
	}
	

	// For the client
	@GetMapping("/client/bookings")
	public String myBooking(Map<String, Object> model) {
		Object connectedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (connectedUser instanceof UserDetails) {
			Date yesterday = new Date((new Date()).getTime() - (1000 * 60 * 60 * 24));
			User user = (User) (connectedUser);
			//List<Reservation> reservations = reservationRepository.findByUserOrderByDateStart(user);
			List<Reservation> reservations = reservationRepository.findByUserAndDateEndAfterOrderByDateStartAsc(user, yesterday);
			System.out.println(reservations);
			model.put("reservations", reservations);
			
			List<Reservation> reservationsOld = reservationRepository.findByUserAndDateEndBeforeOrderByDateStartAsc(user, new Date());
			System.out.println(reservationsOld);
			model.put("old_reservations", reservationsOld);
			
			
			
			return "reservation/bookings";
		}
		return "redirect:/login";
	}

}
