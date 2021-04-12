package ch.heArc.hotelDiscovery.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import ch.heArc.hotelDiscovery.models.Hotel;
import ch.heArc.hotelDiscovery.models.Room;
import ch.heArc.hotelDiscovery.repository.IHotelRepository;
import ch.heArc.hotelDiscovery.services.HotelService;

@Controller
public class ReservationController {
    
    @Autowired 
    IHotelRepository hotelRepository;
    
    @Autowired 
    HotelService hotelService;
    
    @Autowired 
    RoomController roomController;
    
    
    @GetMapping("/view/{hotelId}")
    public  String searchCity(Map<String, Object> model, @PathVariable int hotelId) {
        
    	Optional<Hotel> hotel = hotelRepository.findById(hotelId);
    	
    	if (hotel.isPresent()) {
    		List<Room> rooms = roomController.roomRepository.findByHotel(hotel.get());
			model.put("hotel", hotel.get());
			model.put("rooms", rooms);
	        
	        return "reservation/viewHotel";
    	}
    	
    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    	
    }
    
    

}
