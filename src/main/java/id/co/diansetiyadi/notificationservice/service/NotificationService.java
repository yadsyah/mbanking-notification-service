package id.co.diansetiyadi.notificationservice.service;

import id.co.diansetiyadi.notificationservice.dto.response.BaseResponse;
import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface NotificationService {
    Mono<?> RegisterTokenFirebase(String deviceId, String tokenFcm, String cif);

    BaseResponse senderNotification(NotificationType notificationType, List<String> paramArrayValue, String templateCode, String cif, String accountNo);

    Mono<?> RegisterEmail(String deviceId, String cif, String email);

    void SendNotificationManager(NotificationType notificationType, List<String> paramArrayValue, String templateCode, String cif, String accountNo);
}
