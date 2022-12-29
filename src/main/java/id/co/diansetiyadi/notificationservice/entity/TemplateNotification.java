package id.co.diansetiyadi.notificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "template_notification")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateNotification extends BaseEntity {

    private String templateCode;
    private NotificationType notificationType;
    private boolean bundleNotificationType;
    private String mailSender;
    private String mailSubject;
    private String mailRawHtml;
    private String firebaseFcmTitle;
    private String firebaseFcmContentMessage;
    private String smsContentMessage;
    private String whatsAppContentMessage;
}
