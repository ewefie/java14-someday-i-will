package pl.somday.java14.avatar;


import pl.somday.java14.user.AppUser;

import javax.persistence.*;

@Entity(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private AppUser user;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "file_type")
    private String fileType;

    public Avatar(AppUser user, byte[] data, String fileType) {
        this.data = data;
        this.user = user;
        this.fileType = fileType;
    }

    public Long getId() {
        return id;
    }

    public AppUser getUser() {
        return user;
    }

    public byte[] getData() {
        return data;
    }

    public String getFileType() {
        return fileType;
    }
}
