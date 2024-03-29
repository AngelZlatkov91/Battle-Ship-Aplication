package com.example.battleshipsaplication.model.entity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class Ship extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private long health;
    @Column(nullable = false)
    private long power;

    @Column(nullable = false)
    private LocalDate created;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    public Ship (){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
