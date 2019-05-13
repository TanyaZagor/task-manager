package ru.zagorodnikova.tm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.zagorodnikova.tm.entity.CustomUserDetails;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        List<GrantedAuthority> list = new LinkedList<>();
        list.add(user.getRoleType());
        CustomUserDetails customUserDetails = new CustomUserDetails(user.getLogin(), user.getPassword(), list);
        customUserDetails.setUser(user);
        return customUserDetails;
    }
}
