package com.base.pm.base.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<ENTITY> extends JpaRepository<ENTITY,Integer> {
}
