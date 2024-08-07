package com.neighborhood.authService.authService.service;


import com.neighborhood.authService.authService.dto.SignupDTO;
import com.neighborhood.authService.authService.dto.UserDTO;
import com.neighborhood.authService.authService.exceptions.InvalidRoleException;
import com.neighborhood.authService.authService.exceptions.ResourceNotFoundException;
import com.neighborhood.authService.authService.model.User;
import com.neighborhood.authService.authService.model.enums.Role;
import com.neighborhood.authService.authService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("User with username "+ username +" not found"));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id "+ userId +
                " not found"));
    }

    public User getUsrByEmail(String email) {
        return userRepository.findByUsername(email).orElse(null);
    }


    public UserDTO signUp(SignupDTO signupDTO) {

        Optional<User> user = userRepository.findByUsername(signupDTO.getUsername());
        if(user.isPresent()) {
            throw new BadCredentialsException("User with email already exits "+ signupDTO.getUsername());
        }

        User toBeCreatedUser = modelMapper.map(signupDTO, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        User savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }


    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}











