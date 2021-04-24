package ch.heArc.hotelDiscovery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.heArc.hotelDiscovery.repository.IHotelRepository;



@Service
public class HotelService {

	
	@Autowired
	public HotelService(IHotelRepository hotelRepository) {
	}

	

}
