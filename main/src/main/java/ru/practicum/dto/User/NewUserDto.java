package ru.practicum.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public String name;
    @NotNull
    @NotBlank
    @Email
    private String email;

}
