package com.base.pm.dto;

import com.base.pm.common.client.base.jpa.dto.model.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectsDTO extends BaseDTO {
    private String projectName;
    private Date projectStartDate;
    private Date projectEndDate;
}
