package br.com.danllopes.usermanagment.services;

import br.com.danllopes.usermanagment.domain.entities.Users;
import br.com.danllopes.usermanagment.dtos.UserDTO;
import br.com.danllopes.usermanagment.exceptions.DuplicateEmailException;
import br.com.danllopes.usermanagment.exceptions.LoginAlreadyExistsException;
import br.com.danllopes.usermanagment.exceptions.UserNotFoundException;
import br.com.danllopes.usermanagment.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository ;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<Users> getUsers() {return this.repository.findAll();}

    public Users getOptionalUser(String id) {return this.findUserById(id);}

    public Users createUser(UserDTO data) {
        if (repository.existsByEmail(data.email())) {
            throw new DuplicateEmailException("A user with the same email already exists: " + data.email());
        }

        if (repository.existsByLogin(data.login())) {
            throw new LoginAlreadyExistsException("A user with the same login already exists: " + data.login());
        }

        var newUser = new Users(data);
        newUser.encryptPassword(data.password());
        this.repository.save(newUser);
        return newUser;
    }

    public Users updateUser(UserDTO data, String id) {
        Users user = this.findUserById(id);

        if(this.repository.existsByEmail(data.email()) && !data.email().equals(user.getEmail()))
            throw new DuplicateEmailException("A user with the same email already exists: " + data.email());

        if(this.repository.existsByLogin(data.login()) && !data.login().equals(user.getLogin()))
            throw new LoginAlreadyExistsException("A user with the same login already exists: " + data.login());

        user.setName(data.name());
        user.setEmail(data.email());
        user.setLogin(data.login());
        user.encryptPassword(data.password());

        return user;
    }

    public void removeUser(String id) {
        Users user = this.findUserById(id);
        this.repository.delete(user);
    }

    private Users findUserById(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }
}