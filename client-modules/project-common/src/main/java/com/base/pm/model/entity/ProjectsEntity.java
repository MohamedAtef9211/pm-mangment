package com.base.pm.model.entity;

import com.base.pm.common.client.base.jpa.entity.AuditEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Entity(name ="projects")
@SequenceGenerator(name = "default_gen", sequenceName = "projects_seq", allocationSize = 1)
@Data
public class ProjectsEntity extends AuditEntity {

    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_start_date")
    private Date projectStartDate;
    @Column(name = "project_end_date")
    private Date projectEndDate;
}
