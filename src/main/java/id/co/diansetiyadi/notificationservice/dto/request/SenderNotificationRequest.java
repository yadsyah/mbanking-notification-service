package id.co.diansetiyadi.notificationservice.dto.request;


import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import jakarta.validation.constraints.NotNull;
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
    private String phoneNo;
    @NotNull(message = "isScheduler must be not null!")
    private boolean isScheduler;
    private String dateScheduler;

    public boolean getIsScheduler() {
        return isScheduler;
    }

    public void setIsScheduler(boolean scheduler) {
        isScheduler = scheduler;
    }
}
