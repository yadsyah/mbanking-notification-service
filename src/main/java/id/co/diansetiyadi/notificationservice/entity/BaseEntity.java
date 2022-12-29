package id.co.diansetiyadi.notificationservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Setter
@Getter
public abstract class BaseEntity {

    @Id
    private String id;

    @JsonIgnore
    private boolean deleted;

    @JsonIgnore
    private boolean updated;

    @JsonIgnore
    private  boolean isActive;

    @CreatedBy
    private String createdByUser;

    @Indexed
    @CreatedDate
    private Date creationDate = new Date();

    @LastModifiedDate
    private Date lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedUserId;

    private String appVersion;

}
