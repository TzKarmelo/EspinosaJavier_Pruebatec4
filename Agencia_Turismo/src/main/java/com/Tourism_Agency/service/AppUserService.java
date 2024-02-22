package com.Tourism_Agency.service;

import com.Tourism_Agency.model.AppUser;
import com.Tourism_Agency.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements IAppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public List<AppUser> getAppUsers() {

        if (appUserRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("No users found!");
        }
        return appUserRepository.findAll();
    }

    public AppUser AppUsersListByDni(String dni) {

        if (appUserRepository.findById(dni).isEmpty()) {
            throw new IllegalArgumentException("DNI does not exist!");
        }
        return appUserRepository.findById(dni).orElse(null);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        if (appUserRepository.findById(appUser.getDni()).isPresent()) {
            throw new IllegalArgumentException("DNI exists!");
        }
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser deleteAppUser(String dni) {
        AppUser appUser = appUserRepository.findById(dni).orElse(null);
        if (appUser != null) {
            appUserRepository.delete(appUser);
        } else {
            throw new IllegalArgumentException("DNI does not exist!");
        }
        return appUser;
    }

    @Override
    public AppUser editAppUser(AppUser appUser) {

        if (appUserRepository.findById(appUser.getDni()).isEmpty()) {
            throw new IllegalArgumentException("DNI does not exist!");
        }
        return appUserRepository.save(appUser);
    }

}
