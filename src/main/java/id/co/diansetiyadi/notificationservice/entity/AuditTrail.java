package id.co.diansetiyadi.notificationservice.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AuditTrail {

    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;

}
