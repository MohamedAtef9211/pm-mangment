package com.base.pm.base.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

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
