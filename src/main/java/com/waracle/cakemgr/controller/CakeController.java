package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.service.CakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("cakes")
@RequiredArgsConstructor
public class CakeController {

    @Autowired
    private final CakeService cakeService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public CollectionModel<CakeDto> getAllCakes() {
        return CollectionModel.of(
                cakeService.getAllCakes(),
                linkTo(methodOn(CakeController.class).getAllCakes()).withSelfRel()
        );
    }

    @ResponseStatus(CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public EntityModel<CakeDto> createCake(
            @Valid @RequestBody CakeDto cakeDto
    ) {
        return EntityModel.of(cakeService.createCake(cakeDto));
    }
}
