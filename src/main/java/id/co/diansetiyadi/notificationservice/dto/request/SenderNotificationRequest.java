package id.co.diansetiyadi.notificationservice.dto.request;


import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SenderNotificationRequest extends BaseRequest {

    private String templateCode;
    private String cif;
    private String accountNo;
    private NotificationType notificationType;
    private List<String> paramArrayValue;
    private String email;
}
