package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_connections")
public class UserConnections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
        name = "user_connections_list",
        joinColumns = @JoinColumn(name = "user_connections_id"),
        inverseJoinColumns = @JoinColumn(name = "connected_user_id")
    )
    private List<User> connections;

    public UserConnections() {
        // Default constructor
    }

    public UserConnections(User user, List<User> connections) {
        this.user = user;
        this.connections = connections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getConnections() {
        return connections;
    }

    public void setConnections(List<User> connections) {
        this.connections = connections;
    }
}
