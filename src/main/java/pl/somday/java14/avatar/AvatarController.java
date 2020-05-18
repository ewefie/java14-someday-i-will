package pl.somday.java14.avatar;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.somday.java14.security.user.CurrentUser;
import pl.somday.java14.security.user.UserPrincipal;
import pl.somday.java14.user.AppUserService;

import java.net.URI;

@RestController
@RequestMapping("/users/me/avatar")
public class AvatarController {

    private final AvatarService avatarService;
    private final AppUserService appUserService;

    public AvatarController(final AvatarService avatarService, final AppUserService appUserService) {
        this.avatarService = avatarService;
        this.appUserService = appUserService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ByteArrayResource> getAvatar(@CurrentUser final UserPrincipal userPrincipal) {
        var userAvatar = avatarService.getUsersAvatar(userPrincipal.getId());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(userAvatar.getFileType()))
                .body(new ByteArrayResource(userAvatar.getData()));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ByteArrayResource> uploadAvatar(
            @RequestParam("file") final MultipartFile file,
            @CurrentUser final UserPrincipal userPrincipal) {
        var existingUser = appUserService.getExistingUser(userPrincipal.getId());
        var userAvatar = avatarService.saveAvatar(file, existingUser);
        return ResponseEntity.created(URI.create(""))
                .contentType(MediaType.parseMediaType(userAvatar.getFileType()))
                .body(new ByteArrayResource(userAvatar.getData()));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAvatar(@CurrentUser final UserPrincipal userPrincipal) {
        avatarService.deleteUsersAvatar(userPrincipal.getId());
    }
}
