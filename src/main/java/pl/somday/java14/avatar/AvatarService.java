package pl.somday.java14.avatar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.somday.java14.exceptions.ResourceNotFoundException;
import pl.somday.java14.user.AppUser;
import pl.somday.java14.user.AppUserRepository;

import java.io.IOException;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class AvatarService {

    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    private final AvatarRepository avatarRepository;
    private final AppUserRepository appUserRepository;

    public AvatarService(final AvatarRepository avatarRepository, final AppUserRepository appUserRepository) {
        this.avatarRepository = avatarRepository;
        this.appUserRepository = appUserRepository;
    }

    public Avatar saveAvatar(final MultipartFile file, final AppUser user) {
        try {
            if (!isSupportedType(file.getContentType())) {
                throw new AvatarStorageException("Unsupported file type.");
            }
            var avatar = new Avatar(user, file.getBytes(), file.getContentType());

            user.setAvatar(avatar);
            appUserRepository.save(user);
            return avatar;
        } catch (IOException exp) {
            logger.error(exp.getMessage());
            throw new AvatarStorageException("Could not store file.");
        }
    }

    public void deleteUsersAvatar(final Long userId) {
        avatarRepository.deleteAvatarByUserId(userId);
    }

    public Avatar getUsersAvatar(final Long userId) {
        return avatarRepository.findAvatarByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Avatar does not exist"));
    }

    private boolean isSupportedType(final String fileType) {
        return nonNull(fileType) && (fileType.equals(MimeTypeUtils.IMAGE_JPEG_VALUE) || fileType
                .equals(MimeTypeUtils.IMAGE_PNG_VALUE));
    }
}
