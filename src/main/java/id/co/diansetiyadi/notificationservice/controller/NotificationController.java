package id.co.diansetiyadi.notificationservice.controller;

import id.co.diansetiyadi.notificationservice.dto.request.RegisterEmailRequest;
import id.co.diansetiyadi.notificationservice.dto.request.RegisterTokenFirebaseRequest;
import id.co.diansetiyadi.notificationservice.dto.request.SenderNotificationRequest;
import id.co.diansetiyadi.notificationservice.dto.response.BaseResponse;
import id.co.diansetiyadi.notificationservice.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/sender-notification")
    @ResponseBody
    public Mono<BaseResponse> SenderNotification(@RequestBody SenderNotificationRequest senderNotificationRequest) {
        return Mono.just(notificationService.senderNotification(senderNotificationRequest.getNotificationType(), senderNotificationRequest.getParamArrayValue(),
                senderNotificationRequest.getTemplateCode(), senderNotificationRequest.getCif(), senderNotificationRequest.getAccountNo()));
    }

    @PostMapping("/register-token-firebase")
    public Mono<?> RegisterTokenFirebase(@RequestBody RegisterTokenFirebaseRequest request) {
        return Mono.just(notificationService.RegisterTokenFirebase(request.getDeviceId(), request.getAccountNo(), request.getTokenFirebase(), request.getCif()));
    }

    @PostMapping("/register-email")
    public Mono<?> RegisterEmail(@RequestBody RegisterEmailRequest request) {
        return notificationService.RegisterEmail(request.getDeviceId(), request.getCif(), request.getEmail());
    }
}
