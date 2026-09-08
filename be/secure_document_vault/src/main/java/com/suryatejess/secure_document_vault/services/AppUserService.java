package com.suryatejess.secure_document_vault.services;

import com.suryatejess.secure_document_vault.entities.AppUser;
import com.suryatejess.secure_document_vault.repositories.AppUserRepository;
import com.suryatejess.secure_document_vault.request.RegisterAppUserRequest;
import com.suryatejess.secure_document_vault.response.AppUserDetailResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    //TODO: write a service method that uses loadUserByUsername function and returns UserDetails.toString
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        AppUser user =  appUserRepository.findByUsername(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("user with username " +  username + " not found");
    }

    public void trigger_loadUserByUsername_method(String username){
        UserDetails appUser = loadUserByUsername(username);
        System.out.println(appUser.toString());
        return;
    }

    public List<AppUserDetailResponse> getAllAppUsers(){
        List<AppUser> allUsers = (List<AppUser>) appUserRepository.findAll();
        List<AppUserDetailResponse> response = new ArrayList<>();

        for(AppUser appUser : allUsers){
            AppUserDetailResponse currentRespUser = new AppUserDetailResponse();

            currentRespUser.setAppUserId(appUser.getAppUserId());
            currentRespUser.setEmail(appUser.getEmail());
            currentRespUser.setFirstName(appUser.getFirstName());
            currentRespUser.setLastName(appUser.getLastName());
            currentRespUser.setUsername(appUser.getUsername());

            response.add(currentRespUser);
        }

        return response;
    }

    public void registerAppUser(RegisterAppUserRequest req){
        AppUser appUser = new AppUser();

        appUser.setEmail(req.getEmail());
        appUser.setFirstName(req.getFirstName());
        appUser.setPassword(passwordEncoder.encode(req.getPassword()));
        appUser.setUsername(req.getUsername());
        appUser.setLastName(req.getLastName());

        appUserRepository.save(appUser);
    }

    public AppUserDetailResponse getAppUserByUsername(String username){
        AppUserDetailResponse res = new AppUserDetailResponse();

        AppUser appUser = appUserRepository.findByUsername(username);

        res.setAppUserId(appUser.getAppUserId());
        res.setEmail(appUser.getEmail());
        res.setFirstName(appUser.getFirstName());
        res.setLastName(appUser.getLastName());
        res.setUsername(appUser.getUsername());

        return res;
    }

}
