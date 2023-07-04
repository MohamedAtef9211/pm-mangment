package com.base.pm.controller;

import com.base.pm.base.controller.BaseCRUDController;
import com.base.pm.base.exception.BaseException;
import com.base.pm.dto.ProjectsDTO;
import com.base.pm.security.AuthorizedEndPoint;
import com.base.pm.security.validator.AnyValidator;
import com.base.pm.security.validator.NameValidator;
import com.base.pm.services.ProjectsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

import java.util.List;

import static com.base.pm.config.FeatureConstants.GET_PROJECT;

@RestController
@RequestMapping("/projects")
public class ProjectsController extends BaseCRUDController<ProjectsDTO> {
    private final ProjectsServices service;

    @Autowired
    private FeatureManager manager;

    protected ProjectsController(ProjectsServices projectsServices, ProjectsServices service) {
        super(projectsServices);
        this.service = projectsServices;
    }

    @RequestMapping(path = {"/", "/list"},
            method = {RequestMethod.GET})
    @AuthorizedEndPoint(value = {NameValidator.class,AnyValidator.class})
    @Override
    public ResponseEntity<List<ProjectsDTO>> getAll() {
        if(manager.isActive(GET_PROJECT)){
            return responseOk(service.findAll());
        }
        throw new BaseException("Method Get Projects us Currently not available");
    }
}
