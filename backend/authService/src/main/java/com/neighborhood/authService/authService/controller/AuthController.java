package com.neighborhood.authService.authService.controller;


import com.neighborhood.authService.authService.dto.*;
import com.neighborhood.authService.authService.exceptions.InvalidRoleException;
import com.neighborhood.authService.authService.model.enums.Role;
import com.neighborhood.authService.authService.service.AuthService;
import com.neighborhood.authService.authService.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignupDTO signupDTO) {
        for (Role role : signupDTO.getRoles()) {
            log.debug("Role is {}", role.name());
            if (!isValidRole(role.name())) {

                throw new InvalidRoleException("Invalid role: " + role.name());
            }
        }

        UserDTO userDto = userService.signUp(signupDTO);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDto, HttpServletRequest request,
                                                  HttpServletResponse response) {
        LoginResponseDTO loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("accessToken", loginResponseDto.getAccessToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }
    private boolean isValidRole(String role) {
        try {
            Role.valueOf(role.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}