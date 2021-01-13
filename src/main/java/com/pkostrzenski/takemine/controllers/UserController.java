package com.pkostrzenski.takemine.controllers;


import com.pkostrzenski.takemine.controllers.request_models.*;
import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.Notifier;
import com.pkostrzenski.takemine.models.User;
import com.pkostrzenski.takemine.services.MyUserDetailsService;
import com.pkostrzenski.takemine.services.interfaces.UserService;
import com.pkostrzenski.takemine.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/api/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            return ErrorResponse.create(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        }

        Optional<User> user = userService.findByUsernameOrEmail(authenticationRequest.getUsername());
        if(user.isPresent()){
            final String token = jwtTokenUtil.generateToken(user.get().getUsername());
            return ResponseEntity.ok(new AuthenticationResponse(user.get().getId(), user.get().getUsername(), token));
        }

        return ErrorResponse.create(HttpStatus.BAD_REQUEST, "Internal error"); // should not happen, we checked earlier that the username is OK
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

        User createdUser;
        try {
            createdUser = userService.register(registerRequest.getEmail(), registerRequest.getPassword());
        } catch (ServiceException e){
            return ErrorResponse.create(HttpStatus.CONFLICT, e.getMessage(), e.getErrorCode());
        }

        final String token = jwtTokenUtil.generateToken(createdUser.getUsername());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RegisterResponse(createdUser.getId(), createdUser.getUsername(), token));
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getUserInfo(Authentication authentication, @PathVariable("id") Integer userId){

        if(providedAuthenticationIsIncorrect(authentication, userId))
            return ErrorResponse.unauthorized();

        return ResponseEntity.ok(userService.findById(userId).get()); // ID is OK, because we check it on checkUserAuthentication
    }


    @PostMapping("/api/users/token")
    public ResponseEntity<?> addFirebaseToken(Authentication authentication, @Valid @RequestBody AddFirebaseTokenRequest data) {
        try {
            String username = authentication.getName();
            userService.setFirebaseToken(username, data.getToken());
            return ResponseEntity.ok().body(true);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/users/notifier")
    public ResponseEntity<?> addNotifier(@Valid @RequestBody Notifier data) {
        return ResponseEntity.ok(userService.addNotifier(data));
    }


    @PostMapping("/api/users/{id}/change-password")
    public ResponseEntity<?> changeUserPassword(Authentication authentication,
                                                @NotBlank @PathVariable("id") Integer userId,
                                                @Valid @RequestBody ChangePasswordRequest data){
        if(providedAuthenticationIsIncorrect(authentication, userId))
            return ErrorResponse.unauthorized();

        try {
            return ResponseEntity.ok(userService.changePassword(userId, data.getCurrentPassword(), data.getNewPassword()));
        } catch (ServiceException e){
            return ErrorResponse.create(HttpStatus.CONFLICT, e.getMessage(), e.getErrorCode());
        }
    }

    private boolean providedAuthenticationIsIncorrect(Authentication authentication, Integer userId){
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            return false; // always let admin do everything

        final Optional<User> user = userService.findById(userId);
        return !user.isPresent() || !authentication.getName().equals(user.get().getUsername());
    }
}
