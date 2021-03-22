package ch.heArc.hotelDiscovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.heArc.hotelDiscovery.models.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
