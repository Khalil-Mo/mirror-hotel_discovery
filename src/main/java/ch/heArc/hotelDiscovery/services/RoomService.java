package ch.heArc.hotelDiscovery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.heArc.hotelDiscovery.repository.IRoomRepository;

@Service
public class RoomService {

	@Autowired
	public RoomService(IRoomRepository roomRepository) {
	}

}
