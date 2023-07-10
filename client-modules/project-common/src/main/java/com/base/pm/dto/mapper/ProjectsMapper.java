package com.base.pm.dto.mapper;

import com.base.pm.common.client.base.jpa.mapper.BaseMapper;
import com.base.pm.dto.ProjectsDTO;
import com.base.pm.model.entity.ProjectsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectsMapper extends BaseMapper<ProjectsEntity, ProjectsDTO> {
    ProjectsMapper MAPPER = Mappers.getMapper(ProjectsMapper.class);

}
