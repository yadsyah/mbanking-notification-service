package id.co.diansetiyadi.notificationservice.service;

import org.springframework.stereotype.Service;

import id.co.diansetiyadi.notificationservice.dto.request.SenderNotificationRequest;
import id.co.diansetiyadi.notificationservice.dto.response.BaseResponse;

@Service
public interface NotificationService {
    BaseResponse registerTokenFirebase(String deviceId, String accountNo, String tokenFcm, String cif);
    BaseResponse senderNotification(SenderNotificationRequest request);
    BaseResponse registerEmail(String deviceId, String cif, String accountNo, String email, String appVersion);

}
