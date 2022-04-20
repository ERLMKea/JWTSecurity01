package com.example.jwtsecurity01.security;

import com.example.jwtsecurity01.entity.User;
import com.example.jwtsecurity01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
// Marks this as a component. Now, Spring Boot will handle the creation and management of the MyUserDetailsService Bean
// and you will be able to inject it in other places of your code
public class MyUserDetailsService implements UserDetailsService {

    // Injecting Dependencies
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch User from the DB
        Optional<User> userRes = userRepo.findByEmail(email);
        // No user found
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        // Return a User Details object using the fetched User information
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); // Sets the role of the found user
    }
}
