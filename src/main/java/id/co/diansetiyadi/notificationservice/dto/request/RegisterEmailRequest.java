package id.co.diansetiyadi.notificationservice.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmailRequest extends BaseRequest {
    @NotBlank(message = "field cif must be not blank!")
    private String cif;
    private String accountNo;
    @NotBlank(message = "field cif must be not blank!")
    private String email;
}
