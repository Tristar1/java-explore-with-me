package ru.practicum.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

    @NotBlank
    @NotNull
    @Length(min = 2, max = 250)
    public String name;
    @NotNull
    @NotBlank
    @Email
    @Length(min = 6, max = 254)
    private String email;

}
