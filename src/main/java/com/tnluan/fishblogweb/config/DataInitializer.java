package com.tnluan.fishblogweb.config;

import com.tnluan.fishblogweb.entity.User;
import com.tnluan.fishblogweb.repository.UserRepository;
import com.tnluan.fishblogweb.util.BcryptPass;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {

        boolean existAdmin = userRepository.findByUserNameAndRole("ADMIN", "ADMIN").isPresent();

        if (!existAdmin) {
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
