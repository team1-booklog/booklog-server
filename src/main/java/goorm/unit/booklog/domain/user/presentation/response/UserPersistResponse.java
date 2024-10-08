package goorm.unit.booklog.domain.user.presentation.response;

public record UserPersistResponse(String id) {
    public static UserPersistResponse of(String id){
        return new UserPersistResponse(id);
    }
}
