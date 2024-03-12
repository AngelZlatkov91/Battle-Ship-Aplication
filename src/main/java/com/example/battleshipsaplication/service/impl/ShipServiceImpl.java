package com.example.battleshipsaplication.service.impl;

import com.example.battleshipsaplication.model.dto.CreateShipDTO;
import com.example.battleshipsaplication.model.dto.ShipDTO;
import com.example.battleshipsaplication.model.entity.Category;
import com.example.battleshipsaplication.model.entity.Ship;
import com.example.battleshipsaplication.model.entity.User;
import com.example.battleshipsaplication.model.enums.ShipType;
import com.example.battleshipsaplication.repository.CategoryRepository;
import com.example.battleshipsaplication.repository.ShipRepository;
import com.example.battleshipsaplication.repository.UserRepository;
import com.example.battleshipsaplication.service.ShipService;
import com.example.battleshipsaplication.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;

    private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;

    private final UserRepository userRepository;
    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository, CategoryRepository categoryRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(CreateShipDTO createShipDTO) {
        Optional<Ship> byName = this.shipRepository.findByName(createShipDTO.getName());
        if (byName.isPresent()){
            return false;
        }

        ShipType type = switch (createShipDTO.getCategory()) {
            case 0 -> ShipType.BATTLE;
            case 1 -> ShipType.CARGO;
            case 2 -> ShipType.PATROL;
            default -> ShipType.BATTLE;
        };
        Category category =  this.categoryRepository.findByName(type);
        Optional<User> owner = this.userRepository.findById(this.loggedUser.getId());
        Ship ship = new Ship();
         ship.setName(createShipDTO.getName());
         ship.setPower(createShipDTO.getPower());
         ship.setHealth(createShipDTO.getHealth());
         ship.setCategory(category);
         ship.setCreated(createShipDTO.getCreated());
         ship.setUser(owner.get());

        this.shipRepository.save(ship);
        return true;
    }

    @Override
    public List<ShipDTO> getShipsOwnedBy(long ownerId) {

        return this.shipRepository.findByUserId(ownerId)
                .stream().map(ShipDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ShipDTO> getShipsNotOwnedBy(long id) {

        return this.shipRepository.findByUserIdNot(id)
                .stream().map(ShipDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ShipDTO> getAllSorted() {
        return this.shipRepository.findByOrderByHealthAscNameDescPowerAsc()
                .stream().map(ShipDTO::new).collect(Collectors.toList());
    }


}
