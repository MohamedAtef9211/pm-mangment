package com.base.pm.base.mapper;

import java.util.List;

public interface BaseMapper<ENTITY, DTO> {

    DTO toDTO(ENTITY entity);

    ENTITY toEntity(DTO dto);

    List<DTO> toDTO(List<ENTITY> entityList);

    List<ENTITY> toEntity(List<DTO> dtoList);
}


