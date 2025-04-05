package com.example.auth.service;

import com.example.auth.config.ConfigurationAuth;
import com.example.auth.model.EntityUser;
import com.example.auth.repository.DetailsUserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DetailsUserImpl implements DetailsUser{
    private final DetailsUserRep detailsUserRep;



    @Override
    public EntityUser getDetilsUser(String name) {
        EntityUser user = detailsUserRep.UserDetailsWithName(name);
        if(user != null) return user;
        throw new UsernameNotFoundException("User not fund: " + name);
    }
}
