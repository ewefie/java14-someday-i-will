package pl.somday.java14.avatar;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class TestAvatar {
    public static Avatar fromMultipartFile(MultipartFile file) throws IOException {
        return new Avatar(null, file.getBytes(), file.getContentType());
    }
}
