package id.co.diansetiyadi.notificationservice.entity;


import id.co.diansetiyadi.notificationservice.util.CategoryInboxEnum;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends BaseEntity {

    private String paramArrayValue;
    private String templateCode;
    private String cif;
    private String accountNo;
    private String tokenFCM;
    private NotificationType notificationType;
    private boolean isScheduler;
    private String idScheduler;
    @Email
    private String email;
    private String phoneNo;
    private CategoryInboxEnum categoryInboxEnum;
    private String messageDetailInbox;
}
