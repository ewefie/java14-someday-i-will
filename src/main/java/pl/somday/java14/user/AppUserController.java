package pl.somday.java14.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.somday.java14.security.user.CurrentUser;
import pl.somday.java14.security.user.UserPrincipal;

@RestController
@RequestMapping("/users/me")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(final AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public AppUserDto getCurrentUser(@CurrentUser final UserPrincipal userPrincipal) {
        return appUserService.getUserDtoByAppUserId(userPrincipal.getId());
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@CurrentUser final UserPrincipal userPrincipal) {
        appUserService.deleteUser(userPrincipal.getId());
    }
}
