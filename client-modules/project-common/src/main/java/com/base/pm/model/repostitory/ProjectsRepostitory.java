package com.base.pm.model.repostitory;

import com.base.pm.common.client.base.jpa.repo.BaseRepository;
import com.base.pm.model.entity.ProjectsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepostitory extends BaseRepository<ProjectsEntity> {
}
