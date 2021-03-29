package ch.heArc.hotelDiscovery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.heArc.hotelDiscovery.models.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
