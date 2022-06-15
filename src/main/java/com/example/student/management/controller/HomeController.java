package com.example.student.management.controller;

import com.example.student.management.models.LoginRequest;
import com.example.student.management.models.ResponseObject;
import com.example.student.management.security.JWTUtils;
import com.example.student.management.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObject> createAuthenticateToken(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseObject("",
                    "Incorrect username or password" + e.getMessage(), ResponseObject.Status.STATUS_FAILED));
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseObject(
                jwtUtils.generateToken(userDetailService.loadUserByUsername(loginRequest.getUsername())),
                "JWT token created successful", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/")
    public String homePage() {
        return "<h1>Welcome!<h1>";
    }



}
