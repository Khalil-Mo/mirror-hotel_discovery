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
import ch.heArc.hotelDiscovery.models.Reservation;
import ch.heArc.hotelDiscovery.models.Room;
import ch.heArc.hotelDiscovery.models.User;
import ch.heArc.hotelDiscovery.repository.IHotelRepository;
import ch.heArc.hotelDiscovery.services.HotelService;
import ch.heArc.hotelDiscovery.services.UserService;

@Controller
public class AdminController {
    
    @Autowired 
    IHotelRepository hotelRepository;
    
    @Autowired 
    HotelService hotelService;
    
    @Autowired
    RoomController roomController;
    
    @Autowired
    ReservationController reservationController;
    
    
    @GetMapping("/admin/hotels")
    public  String getAll(Map<String, Object> model) {

    	// Has role admin if access to this page
		model.put("hotels", hotelRepository.findAll());
        
        return "hotel/hotels";
    }
}
