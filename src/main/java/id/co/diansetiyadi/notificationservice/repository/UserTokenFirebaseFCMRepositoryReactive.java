package id.co.diansetiyadi.notificationservice.repository;

import id.co.diansetiyadi.notificationservice.entity.UserTokenFirebaseFCM;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@EnableReactiveMongoAuditing
public interface UserTokenFirebaseFCMRepositoryReactive extends ReactiveMongoRepository<UserTokenFirebaseFCM, String> {

}
