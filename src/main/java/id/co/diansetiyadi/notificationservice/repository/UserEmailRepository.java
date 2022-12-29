package id.co.diansetiyadi.notificationservice.repository;

import id.co.diansetiyadi.notificationservice.entity.UserEmail;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEmailRepository extends MongoRepository<UserEmail, String> {
    Optional<UserEmail> findByCifOrAccountNoOrDeviceId(Sort sort, String cif, String accountNo, String deviceId);
}
