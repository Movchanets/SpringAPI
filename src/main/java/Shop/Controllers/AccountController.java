package Shop.Controllers;

import Shop.DTO.Account.ChangeAvatarDTO;
import Shop.DTO.Google.GoogleAuthDTO;
import Shop.Services.GoogleAuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final GoogleAuthService googleAuthService;
    @PostMapping("/google-auth")
    public ResponseEntity<AuthResponseDTO> googleLogin(@RequestBody GoogleAuthDTO googleAuth) {
        try {
            GoogleIdToken.Payload googleUserInfo = googleAuthService.verify(googleAuth.getToken());
            return ResponseEntity.ok(service.GoogleAuth(googleUserInfo));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/set-avatar")
    public String setAvatar(
            @RequestBody ChangeAvatarDTO request
    ) {
     return service.ChangeImage(request);

    }
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
        var auth = service.login(request);
        if(auth==null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(auth);
    }
}