package goorm.unit.booklog.domain.user.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import goorm.unit.booklog.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import org.springframework.security.core.userdetails.UserDetails;



@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED
)
@Getter
@Entity
public class User extends BaseTimeEntity {

        @Id
        @Column(nullable=false)
        private String id;

        @Column(nullable=false)
        private String name;

        @Column(nullable=false)
        private String password;

        //createdAt과 updatedAt은 BaseTimeEntity에서 자동으로 관리됨

}
