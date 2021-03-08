package com.waracle.cakemgr.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CakeDto {

    private Long id;
    @Size(min = 1, max = 100, message = "Title must between {min} and {max} characters")
    private String title;
    @Size(min = 1, max = 100, message = "Description must between {min} and {max} characters")
    private String description;
    @Size(min = 1, max = 300, message = "Image must between {min} and {max} characters")
    private String image;
}
