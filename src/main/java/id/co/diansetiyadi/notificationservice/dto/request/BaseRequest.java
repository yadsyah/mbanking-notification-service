package id.co.diansetiyadi.notificationservice.dto.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseRequest {

    private String deviceId;
    private String appVersion;

}
