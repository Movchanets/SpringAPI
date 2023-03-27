package Shop.Services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Shop.constants.Roles;
import Shop.entities.RoleEntity;
import Shop.entities.UserEntity;
import Shop.entities.UserRoleEntity;
import Shop.interfaces.ISeedService;
import Shop.repositories.RoleRepository;
import Shop.repositories.UserRepository;
import Shop.repositories.UserRoleRepository;

@Service
@AllArgsConstructor
public class SeedService implements ISeedService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void seedRoleData() {
        if (roleRepository.count() == 0) {
            RoleEntity admin = new RoleEntity().builder()
                    .name(Roles.ADMIN)
                    .build();
            roleRepository.save(admin);

            RoleEntity user = new RoleEntity().builder()
                    .name(Roles.USER    )
                    .build();
            roleRepository.save(user);
        }
    }

    @Override
    public void seedUserData() {

        if(userRepository.count() > 0) return;
        var user = new UserEntity().builder()
                .email("admin@gmail.com")
                .firstName("Микола")
                .lastName("Підкаблучник")
                .phone("098 34 34 221")
                .password(passwordEncoder.encode("123456"))
                .build();
        userRepository.save(user);
        var role = roleRepository.findByName(Roles.ADMIN);
        var ur = new UserRoleEntity().builder()
                .role(role)
                .user(user)
                .build();
        userRoleRepository.save(ur);
    }
}