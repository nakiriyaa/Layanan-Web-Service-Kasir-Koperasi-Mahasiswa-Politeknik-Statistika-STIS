package com.polstatstis.kopma.koperasi_kasir.controller;

import com.polstatstis.kopma.koperasi_kasir.auth.AuthRequest;
import com.polstatstis.kopma.koperasi_kasir.auth.AuthResponse;
import com.polstatstis.kopma.koperasi_kasir.auth.JwtUtil;
import com.polstatstis.kopma.koperasi_kasir.dto.UserDto;
import com.polstatstis.kopma.koperasi_kasir.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @Operation(summary = "User login to get access token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Email and access token", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))}),
        @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content)})

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            String accessToken = jwtUtil.generateAccessToken(authentication);
            AuthResponse response = new AuthResponse(request.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "register a new user")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "user created",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                }
        )
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto request) {
        UserDto user = userService.createUser(request);
        return ResponseEntity.ok().body(user);
    }

}
