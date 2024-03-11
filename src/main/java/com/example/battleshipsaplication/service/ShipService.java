package com.example.battleshipsaplication.service;

import com.example.battleshipsaplication.model.dto.CreateShipDTO;
import com.example.battleshipsaplication.model.dto.ShipDTO;

import java.util.List;

public interface ShipService {
    boolean create(CreateShipDTO createShipDTO);

    List<ShipDTO> getShipsOwnedBy(long id);

    List<ShipDTO> getShipsNotOwnedBy(long id);
}
