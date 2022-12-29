package id.co.diansetiyadi.notificationservice.repository;

import id.co.diansetiyadi.notificationservice.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
