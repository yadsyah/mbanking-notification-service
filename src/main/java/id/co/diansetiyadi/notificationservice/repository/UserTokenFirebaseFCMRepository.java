package id.co.diansetiyadi.notificationservice.repository;

import id.co.diansetiyadi.notificationservice.entity.UserTokenFirebaseFCM;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTokenFirebaseFCMRepository extends MongoRepository<UserTokenFirebaseFCM, String> {

    Optional<UserTokenFirebaseFCM> findByCifOrAccountNoOrDeviceId(Sort sort, String cif, String accountNo, String deviceId);

}
