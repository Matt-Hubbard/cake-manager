package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.service.CakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("cakes")
@RequiredArgsConstructor
public class CakeController {

    @Autowired
    private final CakeService cakeService;

    @GetMapping
    public CollectionModel<CakeDto> getAllCakes() {
        return CollectionModel.of(
                cakeService.getAllCakes(),
                linkTo(methodOn(CakeController.class).getAllCakes()).withSelfRel()
        );
    }

    @PostMapping
    public EntityModel<CakeDto> createCake(
            @RequestBody CakeDto cakeDto
    ) {
        return EntityModel.of(cakeService.createCake(cakeDto));
    }
}
