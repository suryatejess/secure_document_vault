package com.suryatejess.secure_document_vault.controllers;

import com.suryatejess.secure_document_vault.entities.AppUser;
import com.suryatejess.secure_document_vault.request.RegisterAppUserRequest;
import com.suryatejess.secure_document_vault.response.AppUserDetailResponse;
import com.suryatejess.secure_document_vault.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appUser")
public class AppUserController {
    
    @Autowired
    AppUserService appUserService;

    @PostMapping("/trigger_loadUserByUsername_method/{uname}")
    public void trigger_loadUserByUsername_method(@PathVariable String uname){
        System.out.println("====    start   ====");
        appUserService.trigger_loadUserByUsername_method(uname);
        System.out.println("====     end    ====");
    }

    @PostMapping("/")
    public void registerUser(@RequestBody RegisterAppUserRequest req){
        appUserService.registerAppUser(req);
    }

    @GetMapping("/{username}")
    public AppUserDetailResponse getUserByUsername(@PathVariable String username){
        AppUserDetailResponse res = appUserService.getAppUserByUsername(username);
        return res;
    }

    @GetMapping("/")
    public List<AppUserDetailResponse> getAllUsers(){
        return appUserService.getAllAppUsers();
    }


}
