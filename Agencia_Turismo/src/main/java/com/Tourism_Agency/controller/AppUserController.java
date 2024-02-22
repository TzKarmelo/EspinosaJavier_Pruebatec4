package com.Tourism_Agency.controller;

import com.Tourism_Agency.model.AppUser;
import com.Tourism_Agency.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appuser")
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;

    @GetMapping("/list")
    public List<AppUser> listAppUsers() {
        return appUserService.getAppUsers();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> listAppUserDNI(@PathVariable String dni) {

        try {
            return ResponseEntity.ok(appUserService.AppUsersListByDni(dni));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to list appUser... " + e.getMessage());
        }
    }

    @PutMapping("/edit/{dni}")
    public ResponseEntity<String> editAppUser(@PathVariable String dni, @RequestBody AppUser appUser) {
        try {
            appUser.setDni(dni);
            appUserService.editAppUser(appUser);
            return ResponseEntity.ok("AppUser edited!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to edit... " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<String> newAppUser(@RequestBody AppUser appUser) {
        try {
            appUserService.saveAppUser(appUser);
            return ResponseEntity.ok("New appUser added!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add... " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<String> deleteAppUser(@PathVariable String dni) {
        try {
            appUserService.deleteAppUser(dni);
            return ResponseEntity.ok("AppUser deleted!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete... " + e.getMessage());
        }
    }


}
