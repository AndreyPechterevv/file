package com.example.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Integer movieId;

    @NotBlank(message = "Please, provide movie's title!")
    private String title;

    @NotBlank(message = "Please, provide movie's director!")
    private String director;

    @NotBlank(message = "Please, provide movie's title!")
    private String studio;


    private Set<String> movieCast;

    private Integer releaseYear;

    @NotBlank(message = "Please, provide movie's poster")
    private String poster;

    @NotBlank(message = "Please, provide poster url")
    private String posterUrl;
}
