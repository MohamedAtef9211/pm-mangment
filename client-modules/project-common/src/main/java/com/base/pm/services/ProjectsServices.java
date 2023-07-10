package com.base.pm.services;

import com.base.pm.common.client.base.jpa.service.model.BaseCRUDService;
import com.base.pm.dto.ProjectsDTO;
import com.base.pm.dto.mapper.ProjectsMapper;
import com.base.pm.model.entity.ProjectsEntity;
import com.base.pm.model.repostitory.ProjectsRepostitory;
import org.springframework.stereotype.Service;

@Service
public class ProjectsServices extends BaseCRUDService<ProjectsEntity, ProjectsDTO> {
    public ProjectsServices(ProjectsRepostitory repository, ProjectsMapper mapper) {
        super(repository, mapper);
    }
}
