package id.co.diansetiyadi.notificationservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmailRequest extends BaseRequest {
    private String cif;
    private String accountNo;
    private String email;
}
