package goorm.unit.booklog.domain.user.domain;

import java.util.Collection;
import java.util.Collections;

import goorm.unit.booklog.common.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Getter
@Entity
@Builder
@Table(name="\"user\"")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)

public class User extends BaseTimeEntity implements UserDetails {

        @Id
        @Column(nullable=false)
        private String id;

        @Column(nullable=false)
        private String name;

        @Column(nullable=false)
        private String password;

        public static User create(String id, String name, String password) {
                return User.builder()
                        .id(id)
                        .name(name)
                        .password(password)
                        .build();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }

        @Override
        public String getUsername() {
                return id;
        }

        @Override
        public String getPassword(){
                return password;
        }

}
