package id.co.diansetiyadi.notificationservice.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTokenFirebaseRequest extends BaseRequest {
    @NotBlank
    private String cif;
    private String accountNo;
    @NotBlank
    private String tokenFirebase;
}
