package com.base.pm.common.client.base.jpa.controller;

import com.base.pm.common.client.aspect.logging.SkipLogging;
import com.base.pm.common.client.base.controller.BaseController;
import com.base.pm.common.client.base.jpa.dto.model.BaseDTO;
import com.base.pm.common.client.base.jpa.service.model.BaseCRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


public abstract class BaseCRUDController<DTO extends BaseDTO> extends BaseController {

    private final BaseCRUDService baseCRUDService;

    protected BaseCRUDController(BaseCRUDService baseDAOLookupService) {
        this.baseCRUDService = baseDAOLookupService;
    }

    @RequestMapping(path = {"/hello"},
            method = {RequestMethod.GET, RequestMethod.POST})
    public String hello() {
        return "Hello";
    }

    @RequestMapping(path = {"/", "/list"},
            method = {RequestMethod.GET})
    public ResponseEntity<List<DTO>> getAll() {
        return responseOk(baseCRUDService.findAll());
    }

    @RequestMapping(path = {"/id/{id}", "/{id}"},
            method = {RequestMethod.GET})
    public ResponseEntity<DTO> findById(@PathVariable(name = "id") Integer id) throws Throwable {
        return responseOk(baseCRUDService.getById(id));
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<DTO> save(@RequestBody @Validated DTO dto) {
        return responseOk(baseCRUDService.save(dto));
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ResponseEntity<DTO> update(@RequestBody @Validated DTO dto) throws Throwable {
        return responseOk(baseCRUDService.update(dto));
    }
    @SkipLogging
    @RequestMapping(path = {"/id/{id}", "/{id}"},
            method = {RequestMethod.DELETE})
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) throws Throwable {
        return responseOk(baseCRUDService.delete(id));
    }

}
