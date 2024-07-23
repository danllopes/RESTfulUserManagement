package br.com.danllopes.usermanagment.repositories;

import br.com.danllopes.usermanagment.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Users, String> {
    UserDetails findByLogin(String login);
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
}