package id.co.diansetiyadi.notificationservice.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "user_token_firebase_fcm")
@ToString
public class UserTokenFirebaseFCM extends BaseEntity{
    @NotBlank(message = "tokenFcm must not be blank!")
    private String tokenFcm;
    private String deviceId;
    @NotBlank(message = "cif must not be blank!")
    private String cif;
    private String accountNo;
}
