package ch.heArc.hotelDiscovery.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.heArc.hotelDiscovery.models.Hotel;
import ch.heArc.hotelDiscovery.models.Room;
import ch.heArc.hotelDiscovery.models.User;
import ch.heArc.hotelDiscovery.repository.IHotelRepository;
import ch.heArc.hotelDiscovery.repository.IRoomRepository;
import ch.heArc.hotelDiscovery.services.HotelService;
import ch.heArc.hotelDiscovery.services.RoomService;

@Controller
public class RoomController {
	
	@Autowired
	HotelController hotelController;

	@Autowired
	IHotelRepository hotelRepository;

	@Autowired
	HotelService hotelService;

	@Autowired
	IRoomRepository roomRepository;

	@Autowired
	RoomService roomService;

	@GetMapping("/hotel/{hotelId}/room/add")
	public String addRoom(Map<String, Object> model, @PathVariable int hotelId) {

		Optional<Hotel> hotel = hotelController.getHotelById(hotelId);
		if (hotel.isEmpty())
			return "redirect:/hotel/" + hotelId + "/edit";
		Room room = new Room(hotel.get());
		model.put("room", room);
		return "hotel/room/create";
	}
	
	@PostMapping("/hotel/{hotelId}/room/insert")
	public String insertRoom(Room room) {
		Object connectedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	if (connectedUser instanceof UserDetails) {
    		User user = (User)(connectedUser);
    		if (room.getHotel().isTheHotelManager(user)) {
    			roomRepository.save(room);
    		}
    	}
		
		return "redirect:/hotel/" + room.getHotel().getIdHotel() + "/edit";
	}
	
	@GetMapping("/hotel/{hotelId}/room/{roomId}/edit")
	public String editRoom(Map<String, Object> model, @PathVariable int hotelId, @PathVariable int roomId) {
		Optional<Hotel> hotel = hotelController.getHotelById(hotelId);
		if (hotel.isPresent()) {
			Optional<Room> room = roomRepository.findById(roomId);
			if (room.isPresent() && room.get().ownedByThisHotel(hotel.get())) {
				model.put("room", room);
				return "hotel/room/edit";
			}
		}
		
		return "redirect:/hotel/" + hotelId + "/edit";
	}
	
	@PostMapping("/hotel/{hotelId}/room/{roomId}/update")
	public String updateRoom(Room room, @PathVariable int hotelId, @PathVariable int roomId) {
		
		Optional<Hotel> hotel = hotelController.getHotelById(hotelId);
		if (hotel.isPresent()) {
			room.setHotel(hotel.get());
			room.setIdRoom(roomId);
			roomRepository.save(room);
		}
		
		return "redirect:/hotel/" + hotelId + "/room/" + roomId + "/edit";
	}

}
