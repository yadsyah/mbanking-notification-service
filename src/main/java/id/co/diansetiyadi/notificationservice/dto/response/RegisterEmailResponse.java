package id.co.diansetiyadi.notificationservice.dto.response;


import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterEmailResponse {

    private String email;
    private String id;
}
