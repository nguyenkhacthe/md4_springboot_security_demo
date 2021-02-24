package com.cg.security;

import com.cg.security.model.Role;
import com.cg.security.model.User;
import com.cg.security.service.role.IRoleService;
import com.cg.security.service.role.RoleService;
import com.cg.security.service.user.IUserService;
import com.cg.security.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SecurityApplication {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init(){

        List<Role> roles = (List<Role>)roleService.findAll();
        if(roles.isEmpty()){
            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleService.save(roleAdmin);

            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleService.save(roleUser);
        }

        List<User> users = (List<User>)userService.findAll();
        if(users.isEmpty()){

            User admin = new User();
            admin.setUserName("admin");
            admin.setPassWord(passwordEncoder.encode("12345"));
            Role role = new Role();
            role.setId(1L);
            role.setName("ROLE_ADMIN");
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            admin.setRoles(roleSet);
            userService.save(admin);

            User user = new User();
            user.setUserName("user");
            user.setPassWord(passwordEncoder.encode("123457"));
            Role roleUser = new Role();
            roleUser.setId(2L);
            roleUser.setName("ROLE_USER");
            Set<Role> roleSet2 = new HashSet<>();
            roleSet2.add(roleUser);
            user.setRoles(roleSet2);
            userService.save(user);
        }
    }


    public static void main(String[] args) {

        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public IUserService userService(){
        return new UserService();
    }
    @Bean
    public IRoleService roleService(){
        return new RoleService();
    }
}
