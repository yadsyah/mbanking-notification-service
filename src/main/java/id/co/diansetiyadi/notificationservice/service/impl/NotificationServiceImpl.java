package id.co.diansetiyadi.notificationservice.service.impl;

import com.google.gson.Gson;
import id.co.diansetiyadi.notificationservice.dto.response.BaseResponse;
import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import id.co.diansetiyadi.notificationservice.service.KafkaNotificationService;
import id.co.diansetiyadi.notificationservice.strategy.NotificationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NotificationServiceImpl implements id.co.diansetiyadi.notificationservice.service.NotificationService {

    private final NotificationContext notificationContext;

    @Autowired
    public NotificationServiceImpl(NotificationContext notificationContext) {
        this.notificationContext = notificationContext;
    }

    @Override
    public Mono<?> RegisterTokenFirebase(String deviceId, String tokenFcm, String cif) {
        // todo logic persist to entity
        return null;
    }

    @Override
    public BaseResponse senderNotification(NotificationType notificationType, List<String> paramArrayValue, String templateCode, String cif, String accountNo) {
        Map<String, Object> mDataMessage = new HashMap<>();
        mDataMessage.put("notificationType", notificationType);
        mDataMessage.put("paramArrayValue", paramArrayValue);
        mDataMessage.put("templateCode", templateCode);
        mDataMessage.put("cif", cif);
        mDataMessage.put("accountNo", accountNo);
        notificationContext.sendMessage(new Gson().toJson(mDataMessage), notificationType);
        return BaseResponse.builder().data(null).message("Success").responseCode("00").traceId("999").build();
    }

    @Override
    public Mono<?> RegisterEmail(String deviceId, String cif, String email) {

        return null;
    }

    @Override
    public void SendNotificationManager(NotificationType notificationType, List<String> paramArrayValue, String templateCode, String cif, String accountNo) {

    }
}
