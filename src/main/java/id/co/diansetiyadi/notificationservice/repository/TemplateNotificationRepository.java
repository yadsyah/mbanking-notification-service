package id.co.diansetiyadi.notificationservice.repository;

import id.co.diansetiyadi.notificationservice.entity.TemplateNotification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateNotificationRepository extends MongoRepository<TemplateNotification, String> {

    Optional<TemplateNotification> findByTemplateCodeAndIsActiveIsTrue(String templateCode);
}
