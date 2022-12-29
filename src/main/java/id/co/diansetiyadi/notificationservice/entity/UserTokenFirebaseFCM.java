package id.co.diansetiyadi.notificationservice.entity;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Document(collection = "user_token_firebase_fcm")
@ToString
public class UserTokenFirebaseFCM extends BaseEntity{
    private String tokenFcm;
    private String deviceId;
    private String cif;
    private String accountNo;
}
