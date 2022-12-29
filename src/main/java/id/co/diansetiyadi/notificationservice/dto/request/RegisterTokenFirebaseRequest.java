package id.co.diansetiyadi.notificationservice.dto.request;


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
    private String cif;
    private String accountNo;
    private String tokenFirebase;
}
