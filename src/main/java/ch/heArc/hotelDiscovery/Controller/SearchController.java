package ch.heArc.hotelDiscovery.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ch.heArc.hotelDiscovery.models.Hotel;
import ch.heArc.hotelDiscovery.repository.IHotelRepository;

@Controller
public class SearchController {
    
    @Autowired 
    IHotelRepository hotelRepository;
    
    
    @GetMapping("/search/{city}")
    public  String searchCity(Map<String, Object> model, @PathVariable String city) {
        
    	List<Hotel> hotels = hotelRepository.findByCity(city);
    	
    	model.put("hotels", hotels);
        
        return "search/result";
    }
    
    

}
