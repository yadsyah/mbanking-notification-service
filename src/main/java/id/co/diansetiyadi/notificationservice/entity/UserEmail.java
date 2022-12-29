package id.co.diansetiyadi.notificationservice.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_email")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserEmail extends BaseEntity {


    @Email
    @NotBlank(message = "email must not blank!")
    private String email;
    @NotBlank(message = "email must not blank!")
    private String cif;
    private String accountNo;
    private String deviceId;

}
