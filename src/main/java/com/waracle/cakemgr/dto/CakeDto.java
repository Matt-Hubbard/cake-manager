package com.waracle.cakemgr.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CakeDto {

    private Long id;
    @NotNull(message = "Title must not be null")
    @Size(min = 1, max = 100, message = "Title must between {min} and {max} characters")
    private String title;
    @NotNull(message = "Description must not be null")
    @Size(min = 1, max = 100, message = "Description must between {min} and {max} characters")
    private String description;
    @NotNull(message = "Image must not be null")
    @Size(min = 1, max = 300, message = "Image must between {min} and {max} characters")
    private String image;
}
