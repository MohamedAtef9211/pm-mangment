package com.base.pm.controller;

import com.base.pm.aspect.feature.FeatureToggle;
import com.base.pm.aspect.security.AuthorizedEndPoint;
import com.base.pm.aspect.security.PublicEndPoint;
import com.base.pm.aspect.security.validator.AnyValidator;
import com.base.pm.aspect.security.validator.NameValidator;
import com.base.pm.base.controller.BaseCRUDController;
import com.base.pm.dto.ProjectsDTO;
import com.base.pm.services.ProjectsServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.annotation.FeatureAttribute;

import java.util.List;

import static com.base.pm.aspect.feature.MyFeature.GET_PROJECT;

@RestController
@RequestMapping("/projects")
@FeatureToggle(GET_PROJECT)
public class ProjectsController extends BaseCRUDController<ProjectsDTO> {
    private final ProjectsServices service;

    protected ProjectsController(ProjectsServices projectsServices, ProjectsServices service) {
        super(projectsServices);
        this.service = projectsServices;
    }

    @RequestMapping(path = {"/", "/list"},
            method = {RequestMethod.GET})
    @AuthorizedEndPoint(value = {NameValidator.class,AnyValidator.class})
    @Override
    public ResponseEntity<List<ProjectsDTO>> getAll() {
            return responseOk(service.findAll());
    }
}
