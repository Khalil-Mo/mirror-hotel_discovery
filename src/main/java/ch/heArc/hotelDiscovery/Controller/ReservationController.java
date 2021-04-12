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
public class ReservationController {
    
    @Autowired 
    IHotelRepository hotelRepository;
    
    @Autowired 
    HotelService hotelService;
    
    
    @GetMapping("/view/{hotelId}")
    public  String searchCity(Map<String, Object> model, @PathVariable int hotelId) {
        
    	List<Hotel> hotels = hotelRepository.findById(hotelId);
        
        return "search/result";
    }
    
    

}
