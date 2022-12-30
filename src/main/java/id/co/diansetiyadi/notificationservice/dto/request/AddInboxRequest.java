package id.co.diansetiyadi.notificationservice.dto.request;

import id.co.diansetiyadi.notificationservice.util.CategoryInboxEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddInboxRequest extends BaseRequest {

    private String messageDetail;
    @NotBlank(message = "field cif must not be blank!")
    private String cif;
    @NotNull(message = "field categoryInboxEnum must not be null!")
    private CategoryInboxEnum categoryInboxEnum;
}
