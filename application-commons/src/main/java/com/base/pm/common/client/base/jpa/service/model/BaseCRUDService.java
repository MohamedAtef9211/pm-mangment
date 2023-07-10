package com.base.pm.common.client.base.jpa.service.model;


import com.base.pm.common.client.base.jpa.dto.model.BaseDTO;
import com.base.pm.common.client.base.jpa.mapper.BaseMapper;
import com.base.pm.common.client.base.jpa.repo.BaseRepository;

import java.util.List;

public abstract class BaseCRUDService<ENTITY, DTO extends BaseDTO> {

    private final BaseRepository repository;
    private final BaseMapper mapper;

    public BaseCRUDService(BaseRepository repository, BaseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DTO getById(Integer id) throws Throwable {
        ENTITY entity = (ENTITY) repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        return (DTO) mapper.toDTO(entity);
    }

    public DTO save(DTO dto) {
        ENTITY entity = (ENTITY) mapper.toEntity(dto);
        entity = (ENTITY) repository.save(entity);
        return (DTO) mapper.toDTO(entity);
    }

    public DTO update(DTO dto) throws Throwable {
        ENTITY entity = (ENTITY) mapper.toEntity(dto);
        entity = (ENTITY) repository.save(entity);
        return (DTO) mapper.toDTO(entity);
    }

    public String delete(Integer id) {
        repository.deleteById(id);
        return "Record with id " + id + " has been deleted successfully";
    }

    public List<DTO> findAll() {
        List entities = repository.findAll();
        return mapper.toDTO(entities);
    }
}
