package com.Tourism_Agency.repository;

import com.Tourism_Agency.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {


}
