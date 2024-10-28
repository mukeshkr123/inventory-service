package com.programmingmukesh.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "mobile"),
                @UniqueConstraint(columnNames = "username")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements UserDetails {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Length(max = 254, message = "Email must not exceed 254 characters")
    private String email;

    @NotBlank(message = "First name cannot be blank")
    @Length(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @Length(max = 255, message = "Intro must not exceed 255 characters")
    private String intro;

    @NotNull(message = "Last login cannot be null")
    private LocalDateTime lastLogin = LocalDateTime.now();

    @Length(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @Length(max = 50, message = "Middle name must not exceed 50 characters")
    private String middleName;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Mobile number should be valid")
    @Length(max = 15, message = "Mobile number must not exceed 15 characters")
    private String mobile;

    @NotBlank(message = "Password cannot be blank")
    @Length(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    private String profile;

    @NotBlank(message = "Username cannot be blank")
    @Length(max = 50, message = "Username must not exceed 50 characters")
    private String username;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
