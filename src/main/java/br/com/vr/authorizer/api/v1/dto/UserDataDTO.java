package br.com.vr.authorizer.api.v1.dto;

import br.com.vr.authorizer.infra.repository.mongo.colections.User;
import br.com.vr.authorizer.infra.repository.mongo.enums.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDTO {
    private String code;
    private String email;
    private String password;
    private ProfileEnum profile;

    public UserDataDTO(User entity) {
        this.code = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.profile = entity.getProfile();
    }
}
