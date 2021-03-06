package pl.somday.java14.security.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.somday.java14.security.jwt.TokenProvider;
import pl.somday.java14.user.AppUser;
import pl.somday.java14.user.AppUserService;

@Service
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthService(final AuthenticationManager authenticationManager, final AppUserService appUserService,
                       final PasswordEncoder passwordEncoder, final TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public String authenticateUser(final LoginRequest loginRequest) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }

    public AppUser registerUser(final SignupRequest signUpRequest) {
        if (appUserService.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistsException("Email address already in use.");
        }
        var user = SignupRequestMapper.map(signUpRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserService.saveUser(user);
    }
}
