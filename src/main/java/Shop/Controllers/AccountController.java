package Shop.Controllers;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Shop.DTO.Account.LoginDTO;
import Shop.DTO.Account.AuthResponseDTO;
import Shop.DTO.Account.RegisterDTO;
import Shop.Services.AccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor

public class AccountController {
    private final AccountService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody RegisterDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(
            @RequestBody LoginDTO request
    ) {
        return ResponseEntity.ok(service.login(request));
    }
}