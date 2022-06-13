package com.example.student.management.security;

import com.example.student.management.models.Users;
import com.example.student.management.repositories.UsersRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findByName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(UserDetailsImpl::new).get();
    }

    public boolean isMatchedId(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<Users> user = usersRepository.findByName(username);
        return user.isPresent() ? user.get().getStudentId() == id : false;
    }
}
