package id.co.diansetiyadi.notificationservice.dto.response;

import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SenderNotificationResponse {

    private String idMessage;
    private NotificationType notificationType;
    private String templateCode;
}
