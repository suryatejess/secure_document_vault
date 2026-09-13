package com.suryatejess.secure_document_vault.controllers;

import com.suryatejess.secure_document_vault.config.AppConfigurationProperties;
import com.suryatejess.secure_document_vault.entities.AppUser;
import com.suryatejess.secure_document_vault.exceptions.WrongUserCredentials;
import com.suryatejess.secure_document_vault.repositories.AppUserRepository;
import com.suryatejess.secure_document_vault.request.AppUserLoginRequest;
import com.suryatejess.secure_document_vault.request.RegisterAppUserRequest;
import com.suryatejess.secure_document_vault.response.AppUserDetailResponse;
import com.suryatejess.secure_document_vault.security.JwtUtil;
import com.suryatejess.secure_document_vault.services.AppUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AppUserController {
    
    @Autowired
    AppUserService appUserService;
    @Autowired
    AppConfigurationProperties properties;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AppUserRepository appUserRepo;

    @PostMapping("/trigger_loadUserByUsername_method/{uname}")
    public void trigger_loadUserByUsername_method(@PathVariable String uname){
        appUserService.trigger_loadUserByUsername_method(uname);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterAppUserRequest req) throws Exception {
        appUserService.registerAppUser(req);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AppUserLoginRequest req, HttpServletResponse response){
        String username = req.getUsername();
        String password = req.getPassword();



        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            UserDetails userDetails = appUserService.loadUserByUsername(username);
            AppUser appUser = (AppUser) userDetails;

            String jwt = jwtUtil.generateToken(appUser);

            ResponseCookie cookie = ResponseCookie.from(properties.getCookie().getName(), jwt)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(properties.getCookie().getExpiresIn())
                    .sameSite("Lax")
                    .build();


            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok("login successful");
        }
        catch (BadCredentialsException | UsernameNotFoundException e){
            throw new WrongUserCredentials("Incorrect username or password");
        }
        catch(Exception e){
            throw new WrongUserCredentials("somethings wrong");
        }


    }

    @GetMapping("/{username}")
    public AppUserDetailResponse getUserByUsername(@PathVariable String username){
        AppUserDetailResponse res = appUserService.getAppUserByUsername(username);
        return res;
    }

    @GetMapping("/me")
    public ResponseEntity<Object> me(Authentication authentication){

        if(authentication == null || !authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authenticated");
        }
        AppUser user = (AppUser) authentication.getPrincipal();

        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public List<AppUserDetailResponse> getAllUsers(){
        return appUserService.getAllAppUsers();
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletResponse response){

        ResponseCookie cookie = ResponseCookie.from(properties.getCookie().getName())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok("logout successful");
    }

}
