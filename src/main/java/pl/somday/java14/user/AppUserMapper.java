package pl.somday.java14.user;

public class AppUserMapper {
    public static AppUserDto map(final AppUser user) {
        return new AppUserDto(user.getName(), user.getEmail());
    }
}
