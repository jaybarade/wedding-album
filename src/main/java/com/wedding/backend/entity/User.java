package com.wedding.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wedding> weddings;

    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Wedding> getWeddings() { return weddings; }
    public void setWeddings(List<Wedding> weddings) { this.weddings = weddings; }

    // Static Builder for compatibility with existing code
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private String password;

        public UserBuilder name(String name) { this.name = name; return this; }
        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder password(String password) { this.password = password; return this; }
        public User build() {
            return new User(name, email, password);
        }
    }
}
