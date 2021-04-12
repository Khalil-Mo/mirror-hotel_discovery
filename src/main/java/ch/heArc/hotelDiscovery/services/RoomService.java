package ch.heArc.hotelDiscovery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.heArc.hotelDiscovery.repository.IRoomRepository;

@Service
public class RoomService {

	private final IRoomRepository roomRepository;

	@Autowired
	public RoomService(IRoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

}
