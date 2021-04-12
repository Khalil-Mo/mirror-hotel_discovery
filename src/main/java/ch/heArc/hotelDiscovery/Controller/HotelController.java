package ch.heArc.hotelDiscovery.Controller;

import java.util.List;
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
import ch.heArc.hotelDiscovery.services.HotelService;
import ch.heArc.hotelDiscovery.services.UserService;

@Controller
public class HotelController {
    
    @Autowired 
    IHotelRepository hotelRepository;
    
    @Autowired 
    HotelService hotelService;
    
    @Autowired
    RoomController roomController;
    
    
    @GetMapping("/hotels")
    public  String getAll(Map<String, Object> model) {
        
    	Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (user instanceof UserDetails) {
    		model.put("hotels", hotelRepository.findByManager((User)user));
    	}
        
        return "hotel/hotels";
    }
    
    @GetMapping("/hotel/create")
    public  String create(Map<String, Object> model) {
        
        model.put("hotel", new Hotel());
        
        return "hotel/create";
    }
    
    @PostMapping("/hotel/insert")
    public  String create(Hotel hotel) {
    	//HotelService.signUpUser(hotel);
    	Object connectedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	if (connectedUser instanceof UserDetails) {
    		User user = (User)(connectedUser);
    		hotel.setManager(user);
        	hotelRepository.save(hotel);
        	return "redirect:/hotel/"+hotel.getIdHotel()+"/edit";
    	}
    	return "redirect:/hotel/create?error";
    }

    @GetMapping("/hotel/{hotelId}/edit")
    public String edit(Map<String, Object> model, @PathVariable int hotelId) {
    	Optional<Hotel> hotel = getHotelById(hotelId);
    	if (hotel.isPresent()) {
    		List<Room> rooms = roomController.roomRepository.findByHotel(hotel.get());
			model.put("hotel", hotel.get());
			model.put("rooms", rooms);
			return "hotel/edit";
    	}
    	//return "hotel/edit";
    	return "redirect:/hotels";
    }
    
    @PostMapping("/hotel/edit")
    public String update(Hotel hotel) {
    	Object connectedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	System.out.println("edit");
    	if (connectedUser instanceof UserDetails) {
			hotel.setManager((User)(connectedUser));
        	hotelRepository.save(hotel);
        	return "redirect:/hotel/"+hotel.getIdHotel()+"/edit";
    	}
    	return "redirect:/hotels";
    }
    
    @GetMapping("/hotel/{hotelId}/delete")
    public String delete(Map<String, Object> model, @PathVariable int hotelId) {
    	Optional<Hotel> hotel = getHotelById(hotelId);
    	if (hotel.isPresent()) {
    		hotelRepository.delete(hotel.get());
    	}
    	//return "hotel/edit";
    	return "redirect:/hotels";
    }
    
    public Optional<Hotel> getHotelById(int hotelId) {
    	Object connectedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
    	if (hotelOpt.isEmpty())
    		return hotelOpt;
    	Hotel hotel = hotelOpt.get();
    	
    	System.out.println("Hotel manager: " + hotel.getManager());
    	
    	if (connectedUser instanceof UserDetails) {
    		if (hotel.isTheHotelManager((User)connectedUser)) {
    			return hotelOpt;
    		}
    	}
    	return Optional.empty();
    }
    

}
