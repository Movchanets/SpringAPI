package Shop.Services;

import Shop.DTO.Account.LoginDTO;
import Shop.DTO.Account.AuthResponseDTO;
import Shop.DTO.Account.RegisterDTO;
import Shop.configuration.captcha.CaptchaSettings;
import Shop.configuration.captcha.GoogleResponse;
import Shop.configuration.security.JwtService;
import Shop.constants.Roles;
import Shop.entities.Provider;
import Shop.entities.UserEntity;
import Shop.entities.UserRoleEntity;
import Shop.repositories.RoleRepository;
import Shop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Shop.repositories.UserRoleRepository;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CaptchaSettings captchaSettings;
    private final RestOperations restTemplate;
    protected static final String RECAPTCHA_URL_TEMPLATE = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    public AuthResponseDTO register(RegisterDTO request) {
        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone("098 34 34 221")
                .provider(Provider.LOCAL)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        var role = roleRepository.findByName(Roles.USER);
        var ur = new UserRoleEntity().builder()
                .user(user)
                .role(role)
                .build();
        userRoleRepository.save(ur);

        var jwtToken = jwtService.generateAccessToken(user);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponseDTO login(LoginDTO request) {
        String url = String.format(RECAPTCHA_URL_TEMPLATE, captchaSettings.getSecret(), request.getReCaptchaToken());
        final GoogleResponse googleResponse = restTemplate.getForObject(url, GoogleResponse.class);
        if (!googleResponse.isSuccess()) {   //перевіряємо чи запит успішний
            //throw new Exception("reCaptcha was not successfully validated");
            return null;
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateAccessToken(user);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public void processOAuthPostLogin(String email) {
        Optional<UserEntity> existUser = repository.findByEmail(email);
        if (!existUser.isPresent()) {

            UserEntity newUser = UserEntity.builder()
                    .email(email)
                    .provider(Provider.GOOGLE)
                    .build();

            repository.save(newUser);


        }
    }
}
