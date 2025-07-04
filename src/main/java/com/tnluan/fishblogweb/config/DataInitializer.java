package com.tnluan.fishblogweb.config;

import com.tnluan.fishblogweb.entity.User;
import com.tnluan.fishblogweb.repository.UserRepository;
import com.tnluan.fishblogweb.util.BcryptPass;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * DataInitializer is a component that runs automatically when the application starts.
 * It checks if a default ADMIN user exists in the database.
 * If not, it creates one with default credentials.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;

    /**
     * This method is executed once after the bean is fully initialized.
     * It ensures that a default ADMIN user is present in the database
     * to allow administrators to log in on a fresh system.
     */
    @PostConstruct
    public void init() {

        // Check if an ADMIN user with username "ADMIN" already exists
        boolean existAdmin = userRepository.findByUserNameAndRole("ADMIN", "ADMIN").isPresent();

        if (!existAdmin) {
            // If not found, create a default ADMIN account
            User defaultAdmin = new User();
            defaultAdmin.setUserName("ADMIN");
            defaultAdmin.setFullName("Default Admin");
            defaultAdmin.setPassword(BcryptPass.encrypt("ADMIN"));
            defaultAdmin.setRole("ADMIN");
            defaultAdmin.setEmail("admin@fishblog.com");

            userRepository.save(defaultAdmin);
            System.out.println("✅ Default ADMIN user created.");
        } else {
            System.out.println("ℹ️ ADMIN user already exists.");
        }
    }
}
