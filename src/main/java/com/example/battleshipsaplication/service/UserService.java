package com.example.battleshipsaplication.service;

import com.example.battleshipsaplication.model.dto.LoginDTO;
import com.example.battleshipsaplication.model.dto.UserRegistrationDTO;

public interface UserService {


    boolean register(UserRegistrationDTO userRegistrationDTO);

    boolean login(LoginDTO loginDTO);
}
