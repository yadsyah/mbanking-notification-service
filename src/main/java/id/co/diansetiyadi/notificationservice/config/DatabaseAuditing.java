package id.co.diansetiyadi.notificationservice.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseAuditing implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        String uname = "system";

        return Optional.of(uname);
    }
}
