package com.event.metro.model.dto;

import com.event.metro.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private User user;
    private String jwt;

    public LoginResponseDTO() {
        super();
    }

    public LoginResponseDTO(User user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }
}
