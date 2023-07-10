package com.base.pm.common.client.base.jpa.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDTO {

    private Integer id;

    @JsonIgnore
    private LocalDateTime created;

    @JsonIgnore
    private LocalDateTime modified;

    @JsonIgnore
    public Boolean isNew(){
        return id == null;
    }


}
