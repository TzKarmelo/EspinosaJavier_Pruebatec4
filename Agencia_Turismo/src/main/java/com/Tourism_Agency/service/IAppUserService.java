package com.Tourism_Agency.service;

import com.Tourism_Agency.model.AppUser;

import java.util.List;

public interface IAppUserService {

    public List<AppUser> getAppUsers();
    public AppUser AppUsersListByDni(String dni);
    public AppUser saveAppUser(AppUser appUser);
    public AppUser deleteAppUser(String dni);
    public AppUser editAppUser(AppUser appUser);

}
