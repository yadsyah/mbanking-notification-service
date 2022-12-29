package id.co.diansetiyadi.notificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "scheduler_notification")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerNotification extends BaseEntity{

    private boolean isExecute;
    private Date schedulerDate;

}
