package id.co.diansetiyadi.notificationservice.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseRequest {

    @NotBlank(message="field deviceId must not be blank!")
    private String deviceId;
    @NotBlank(message="field appVersion must not be blank!")
    private String appVersion;

}
