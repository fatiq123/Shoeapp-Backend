package com.fatiq.shoeapp.config;

import com.fatiq.shoeapp.entity.User;
import com.fatiq.shoeapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

// For security, the first admin should be created when the app starts,
// but only if no admin exists. We’ll use a CommandLineRunner to seed an admin user.

//On app startup, checks if a user with username admin exists.
//If not, creates an admin with ROLE_USER and ROLE_ADMIN.
//Credentials: username: admin, password: admin123.
//Logs the action for traceability.

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@shoeapp.com");
            admin.setRoles(new HashSet<>() {{
                add("ROLE_USER");
                add("ROLE_ADMIN");
            }});
            userRepository.save(admin);
            logger.info("Initial admin user created with username: admin");
        } else {
            logger.info("Admin user already exists, skipping initialization");
        }
    }
}
