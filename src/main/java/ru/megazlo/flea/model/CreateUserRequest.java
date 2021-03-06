package ru.megazlo.flea.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.megazlo.flea.model.ext.FieldMatch;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldMatch(first = "password", second = "confirmPassword")
public class CreateUserRequest {
    @NotNull
    @Size(min = 3, max = 14)
    private String username;
    @NotNull @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 14)
    private String password;
    private String confirmPassword;
}
