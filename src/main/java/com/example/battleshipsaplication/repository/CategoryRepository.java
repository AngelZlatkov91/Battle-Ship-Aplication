package com.example.battleshipsaplication.repository;

import com.example.battleshipsaplication.model.entity.Category;
import com.example.battleshipsaplication.model.enums.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Category findByName(ShipType name);
}
