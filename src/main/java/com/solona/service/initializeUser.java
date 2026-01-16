package com.solona.service;
import com.solona.domain.USER_ROLE;
import com.solona.modal.User;
import com.solona.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class initializeUser implements CommandLineRunner{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        String adminUsername = "tusharvaish02@gmail.com";

        if (userRepository.findByEmail(adminUsername)==null) {
            User adminUser = new User();

            adminUser.setPassword(passwordEncoder.encode("tusharAdmin"));
            adminUser.setFullName("TusharAdmin");
            adminUser.setEmail(adminUsername);
            adminUser.setRole(USER_ROLE.ROLE_ADMIN);

            User admin=userRepository.save(adminUser);
        }
    }
}
