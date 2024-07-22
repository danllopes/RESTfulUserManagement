package br.com.danllopes.usermanagment.domain.entities;

import br.com.danllopes.usermanagment.dtos.UserDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "users")
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private String login;
    private String password;

    public Users() {}

    public Users(UserDTO data, String password) {
        this.name = data.name();
        this.email = data.email();
        this.login = data.login();
        this.password = data.password();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
