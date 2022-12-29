package id.co.diansetiyadi.notificationservice.service.impl;

import com.google.gson.Gson;
import id.co.diansetiyadi.notificationservice.dto.response.BaseResponse;
import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import id.co.diansetiyadi.notificationservice.entity.UserTokenFirebaseFCM;
import id.co.diansetiyadi.notificationservice.repository.UserTokenFirebaseFCMRepository;
import id.co.diansetiyadi.notificationservice.service.NotificationService;
import id.co.diansetiyadi.notificationservice.strategy.NotificationContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationContext notificationContext;
    private final UserTokenFirebaseFCMRepository userTokenFirebaseFCMRepository;

    @Autowired
    public NotificationServiceImpl(NotificationContext notificationContext, UserTokenFirebaseFCMRepository userTokenFirebaseFCMRepository) {
        this.notificationContext = notificationContext;
        this.userTokenFirebaseFCMRepository = userTokenFirebaseFCMRepository;
    }

    @Transactional
    @Override
    public BaseResponse RegisterTokenFirebase(String deviceId, String accountNo, String tokenFcm, String cif) {
        // todo logic persist to entity
        UserTokenFirebaseFCM checkExist = userTokenFirebaseFCMRepository.findByCifOrAccountNoOrDeviceId(Sort.by("createdDate").descending(), cif, accountNo, deviceId).orElse(null);

        if (null == checkExist) {
            UserTokenFirebaseFCM newUserTokenFirebase = new UserTokenFirebaseFCM();
            newUserTokenFirebase.setAccountNo(accountNo);
            newUserTokenFirebase.setCif(cif);
            newUserTokenFirebase.setDeviceId(deviceId);
            newUserTokenFirebase.setTokenFcm(tokenFcm);
            newUserTokenFirebase.setActive(true);
            userTokenFirebaseFCMRepository.save(newUserTokenFirebase);
            return BaseResponse.builder()
                    .traceId("777")
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
                .traceId("777")
                .responseCode("00")
                .message("Success")
                .data(null)
                .build();
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

}
