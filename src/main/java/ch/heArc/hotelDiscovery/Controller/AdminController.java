package ch.heArc.hotelDiscovery.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.heArc.hotelDiscovery.repository.IHotelRepository;
import ch.heArc.hotelDiscovery.services.HotelService;

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
