package br.com.vr.authorizer.domain;

import br.com.vr.authorizer.domain.dto.UserDataDTO;
import br.com.vr.authorizer.infra.adapter.enums.ProfileEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@NotBlank
@Data
public class UserPort {

    private String _id;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private LocalDateTime deleted;

    private String name;

    private String email;

    private String password;

    private ProfileEnum profile;

    private boolean active;

    public UserPort() {
    }

    public UserPort(String _id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, LocalDateTime deleted,
                    String name, String email, String password, ProfileEnum profile, boolean active) {
        this._id = _id;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.deleted = deleted;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.active = active;
    }

    public UserPort(UserDataDTO userDataDTO) {
        this.name = userDataDTO.getEmail();
        this.email = userDataDTO.getEmail();
        this.password = userDataDTO.getPassword();
        this.profile = userDataDTO.getProfile();
    }

    public UserDataDTO toUser() {
        return new UserDataDTO(this._id, this.email, this.password, this.profile);
    }
}
