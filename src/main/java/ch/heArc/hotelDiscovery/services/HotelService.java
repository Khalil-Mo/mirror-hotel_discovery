package ch.heArc.hotelDiscovery.services;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.heArc.hotelDiscovery.models.User;
import ch.heArc.hotelDiscovery.repository.IHotelRepository;
import ch.heArc.hotelDiscovery.repository.IUserRepository;



@Service
public class HotelService {

	
	private final IHotelRepository hotelRepository;
	
	@Autowired
	public HotelService(IHotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	

}
