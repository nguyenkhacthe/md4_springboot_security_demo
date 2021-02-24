package com.cg.security.service.user;

import com.cg.security.model.User;
import com.cg.security.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUserName(String username);
}
