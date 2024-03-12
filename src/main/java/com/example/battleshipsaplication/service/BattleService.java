package com.example.battleshipsaplication.service;

import com.example.battleshipsaplication.model.dto.StartBattleDTO;

public interface BattleService {

    void attack(StartBattleDTO startBattleDTO);
}
