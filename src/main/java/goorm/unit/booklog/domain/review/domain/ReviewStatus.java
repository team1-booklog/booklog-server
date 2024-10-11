package goorm.unit.booklog.domain.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewStatus {
    ACTIVE("활성화", "활성화된 게시물 입니다."),
    INACTIVE("비활성화", "삭제되어 보이지 않는 게시물 입니다.");

    private String key;
    private String description;
}
