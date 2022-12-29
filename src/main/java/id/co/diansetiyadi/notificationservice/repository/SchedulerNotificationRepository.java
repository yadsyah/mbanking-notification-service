package id.co.diansetiyadi.notificationservice.repository;

import id.co.diansetiyadi.notificationservice.entity.SchedulerNotification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchedulerNotificationRepository extends MongoRepository<SchedulerNotification, String> {
}
