package com.example.battleshipsaplication.service.impl;

import com.example.battleshipsaplication.model.dto.LoginDTO;
import com.example.battleshipsaplication.model.dto.UserRegistrationDTO;
import com.example.battleshipsaplication.model.entity.User;
import com.example.battleshipsaplication.repository.UserRepository;
import com.example.battleshipsaplication.service.UserService;
import com.example.battleshipsaplication.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final LoggedUser loggedUser;
  @Autowired
    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
      this.loggedUser = loggedUser;
  }

    @Override
    public boolean register(UserRegistrationDTO userRegistrationDTO) {
         if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
             return false;
         }
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
        if (byEmail.isPresent()){
            return false;
        }
        Optional<User> byUserName = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
        if (byUserName.isPresent()){
            return false;
        }


        User user = new User();
         user.setUsername(userRegistrationDTO.getUsername());
         user.setEmail(userRegistrationDTO.getEmail());
         user.setPassword(userRegistrationDTO.getPassword());
         user.setFullName(userRegistrationDTO.getFullName());
         this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(LoginDTO loginDTO) {
        Optional<User> findUser = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (findUser.isEmpty()) {
          return false;
         }
          this.loggedUser.login(findUser.get());
        return true;
    }
}
