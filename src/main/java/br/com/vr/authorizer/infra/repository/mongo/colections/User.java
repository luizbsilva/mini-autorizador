package br.com.vr.authorizer.infra.repository.mongo.colections;

import br.com.vr.authorizer.infra.adapter.dao.port.UserPort;
import br.com.vr.authorizer.infra.repository.mongo.enums.ProfileEnum;
import br.com.vr.authorizer.util.ConstantsColections;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = ConstantsColections.COLECTION_USER)
public class User extends BaseDomain {

    private String name;

    private String email;

    private String password;

    private ProfileEnum profile;

    private boolean active;

    public UserPort toUser() {
        return new UserPort(this._id, this.createdDate, this.getDeleted(),
                this.lastModifiedDate, this.name, this.email, this.password, this.profile, this.active = active);
    }

}
