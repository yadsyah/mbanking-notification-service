package id.co.diansetiyadi.notificationservice.service;

import id.co.diansetiyadi.notificationservice.dto.request.SenderNotificationRequest;
import id.co.diansetiyadi.notificationservice.dto.response.BaseResponse;
import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface NotificationService {
    BaseResponse registerTokenFirebase(String deviceId, String accountNo, String tokenFcm, String cif);

    BaseResponse senderNotification(SenderNotificationRequest request);

    BaseResponse registerEmail(String deviceId, String cif, String accountNo, String email, String appVersion);

}
