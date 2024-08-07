package com.neighborhood.authService.authService.controller;


import com.neighborhood.authService.authService.dto.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

    @GetMapping("/")
    public ResponseEntity<LoginResponseDTO> greet(){
        LoginResponseDTO response = new LoginResponseDTO(1L, "This is token");
        return ResponseEntity.ok(response);
    }
}
