package com.example.battleshipsaplication.service.impl;

import com.example.battleshipsaplication.model.dto.StartBattleDTO;
import com.example.battleshipsaplication.model.entity.Ship;
import com.example.battleshipsaplication.repository.ShipRepository;
import com.example.battleshipsaplication.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleServiceImpl implements BattleService {
    private final ShipRepository shipRepository;
    @Autowired
    public BattleServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public void attack(StartBattleDTO startBattleDTO) {
        Optional<Ship> attacker = this.shipRepository.findById((long) startBattleDTO.getAttackerId());
        Optional<Ship> defender = this.shipRepository.findById((long) startBattleDTO.getDefenderId());

        if (attacker.isEmpty() || defender.isEmpty()) {
            throw new NoSuchElementException();
        }
        Ship attackerShip = attacker.get();
        Ship defenderShip = defender.get();

        long newDefenderHealth = defenderShip.getHealth() - attackerShip.getPower();
        if (newDefenderHealth <=0) {
            this.shipRepository.delete(defenderShip);
        } else {
            defenderShip.setHealth(newDefenderHealth);
            this.shipRepository.save(defenderShip);
        }


    }
}
