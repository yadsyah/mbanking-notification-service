package id.co.diansetiyadi.notificationservice.service.impl;

import com.google.gson.Gson;
import id.co.diansetiyadi.notificationservice.advice.EmailNotFoundException;
import id.co.diansetiyadi.notificationservice.advice.TokenFirebaseNotFoundException;
import id.co.diansetiyadi.notificationservice.dto.request.SenderNotificationRequest;
import id.co.diansetiyadi.notificationservice.dto.response.BaseResponse;
import id.co.diansetiyadi.notificationservice.dto.response.RegisterEmailResponse;
import id.co.diansetiyadi.notificationservice.dto.response.SenderNotificationResponse;
import id.co.diansetiyadi.notificationservice.entity.*;
import id.co.diansetiyadi.notificationservice.repository.*;
import id.co.diansetiyadi.notificationservice.service.NotificationService;
import id.co.diansetiyadi.notificationservice.strategy.NotificationContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationContext notificationContext;
    private final UserTokenFirebaseFCMRepository userTokenFirebaseFCMRepository;
    private final UserEmailRepository userEmailRepository;
    private final TemplateNotificationRepository templateNotificationRepository;
    private final SchedulerNotificationRepository schedulerNotificationRepository;
    private final NotificationRepository notificationRepository;
    private final Gson gson;


    @Autowired
    public NotificationServiceImpl(NotificationContext notificationContext, UserTokenFirebaseFCMRepository userTokenFirebaseFCMRepository, UserEmailRepository userEmailRepository, TemplateNotificationRepository templateNotificationRepository, SchedulerNotificationRepository schedulerNotificationRepository, NotificationRepository notificationRepository, Gson gson) {
        this.notificationContext = notificationContext;
        this.userTokenFirebaseFCMRepository = userTokenFirebaseFCMRepository;
        this.userEmailRepository = userEmailRepository;
        this.templateNotificationRepository = templateNotificationRepository;
        this.schedulerNotificationRepository = schedulerNotificationRepository;
        this.notificationRepository = notificationRepository;
        this.gson = gson;
    }

    @Transactional
    @Override
    public BaseResponse registerTokenFirebase(String deviceId, String accountNo, String tokenFcm, String cif) {
        // todo logic persist to entity
        UserTokenFirebaseFCM checkExist = userTokenFirebaseFCMRepository.findByCifOrAccountNoOrDeviceId(Sort.by("lastModifiedDate").descending(), cif, accountNo, deviceId).orElse(null);

        if (null == checkExist) {
            UserTokenFirebaseFCM newUserTokenFirebase = new UserTokenFirebaseFCM();
            newUserTokenFirebase.setAccountNo(accountNo);
            newUserTokenFirebase.setCif(cif);
            newUserTokenFirebase.setDeviceId(deviceId);
            newUserTokenFirebase.setTokenFcm(tokenFcm);
            newUserTokenFirebase.setActive(true);
            userTokenFirebaseFCMRepository.save(newUserTokenFirebase);
            return BaseResponse.builder()
                    .traceId(UUID.randomUUID().toString())
                    .responseCode("00")
                    .message("Success")
                    .data(null)
                    .build();
        }

        if (StringUtils.isNotBlank(tokenFcm)) {
            Objects.requireNonNull(checkExist).setTokenFcm(tokenFcm);
            checkExist.setActive(true);
            userTokenFirebaseFCMRepository.save(checkExist);
        }

        return BaseResponse.builder()
                .traceId(UUID.randomUUID().toString())
                .responseCode("00")
                .message("Success")
                .data(null)
                .build();
    }

    @SneakyThrows
    @Override
    public BaseResponse senderNotification(SenderNotificationRequest request) {

//        templateNotificationRepository.findByTemplateCodeAndIsActiveIsTrue(request.getTemplateCode()).orElseThrow(() -> new TemplateNotificationNotFoundException("template notification not found!"));
        Notification notification = new Notification();
        notification.setNotificationType(request.getNotificationType());
        notification.setCif(request.getCif());
        notification.setAccountNo(request.getAccountNo());
        notification.setTemplateCode(request.getTemplateCode());
        notification.setAppVersion(request.getAppVersion());
        notification.setScheduler(false);
        notification.setAppVersion(request.getAppVersion());
        notification.setPhoneNo(request.getPhoneNo());
        notification.setParamArrayValue(Base64.getEncoder().encodeToString(new Gson().toJson(request.getParamArrayValue()).getBytes()));

        if (request.getNotificationType().equals(NotificationType.FIREBASE)) {
            UserTokenFirebaseFCM userTokenFirebaseFCM = userTokenFirebaseFCMRepository.findByCifOrAccountNoOrDeviceId(
                    Sort.by("lastModifiedDate").descending(), request.getCif(), request.getAccountNo(),
                    request.getDeviceId()).orElseThrow(() -> new TokenFirebaseNotFoundException("token firebase not found!"));
            notification.setTokenFCM(userTokenFirebaseFCM.getTokenFcm());
        }

        if (request.getNotificationType().equals(NotificationType.MAIL)) {
            UserEmail userEmail = userEmailRepository.findByCifOrAccountNoOrDeviceId(
                    Sort.by("lastModifiedDate").descending(), request.getCif(), request.getAccountNo(),
                    request.getDeviceId()).orElseThrow(()-> new EmailNotFoundException("email not found!"));
            notification.setEmail(userEmail.getEmail());
        }

        if (request.getIsScheduler()) {
            notification.setScheduler(true);
            SchedulerNotification schedulerNotification = new SchedulerNotification();
            schedulerNotification.setExecute(false);
            schedulerNotification.setSchedulerDate(new SimpleDateFormat("dd-MM-yyyy").parse(request.getDateScheduler()));
            String idSchedulerNotification = schedulerNotificationRepository.save(schedulerNotification).getId();
            notification.setIdScheduler(idSchedulerNotification);
        }
        log.info("notification : {}", gson.toJson(notification));
        String idNotification = notificationRepository.save(notification).getId();

        notificationContext.sendMessage(new Gson().toJson(notification), request.getNotificationType());
        return BaseResponse.builder()
                .data(SenderNotificationResponse.builder()
                                .notificationType(request.getNotificationType())
                                .templateCode(request.getTemplateCode())
                                .idMessage(idNotification)
                                .build())
                .message("Success")
                .responseCode("00")
                .traceId(UUID.randomUUID().toString())
                .build();
    }

    @Override
    public BaseResponse registerEmail(String deviceId, String accountNo, String cif, String email, String appVersion) {
        UserEmail checkExist = userEmailRepository.findByCifOrAccountNoOrDeviceId(Sort.by("lastModifiedDate").descending(), cif, accountNo, deviceId).orElse(null);
        if (null == checkExist) {
            UserEmail newUserEmail = new UserEmail();
            newUserEmail.setEmail(email);
            newUserEmail.setActive(true);
            newUserEmail.setCif(cif);
            newUserEmail.setAccountNo(accountNo);
            newUserEmail.setDeviceId(deviceId);
            newUserEmail.setAppVersion(appVersion);
            String idUserEmail = userEmailRepository.save(newUserEmail).getId();
            return BaseResponse.builder().responseCode("00").message("Success").traceId(UUID.randomUUID().toString()).data(RegisterEmailResponse.builder().email(email).id(idUserEmail)).build();
        }
        checkExist.setEmail(email);
        return BaseResponse.builder().responseCode("00").message("Success").traceId(UUID.randomUUID().toString()).data(RegisterEmailResponse.builder().email(email).id(checkExist.getId()).build()).build();

    }

}
