package com.example.battleshipsaplication.model.entity;

import com.example.battleshipsaplication.model.enums.ShipType;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(columnDefinition = "text")
    private String description;

    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ShipType name;

    public ShipType getName() {
        return name;
    }

    public void setName(ShipType name) {
        this.name = name;
    }

    public Category(){}

    public Category(ShipType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
