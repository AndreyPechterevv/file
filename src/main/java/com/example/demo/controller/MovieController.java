package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.exceptions.EmptyFileException;
import com.example.demo.repositiries.MovieRepository;
import com.example.demo.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<MovieDto> addMovie(@RequestPart MultipartFile file,
                                             @RequestPart String movieDto) throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFileException("Load file");
        }
        MovieDto movie = convertToMovieDto(movieDto);
        return new ResponseEntity<>(movieService.addMovie(movie, file), HttpStatus.CREATED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Integer movieId) {
                return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Integer id,
                                                @RequestPart MultipartFile file,
                                                @RequestPart String movieDto) throws IOException {
        if (file.isEmpty()) file = null;
        return ResponseEntity.ok(movieService.updateMovie(id,convertToMovieDto(movieDto),file));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }

    private MovieDto convertToMovieDto(String movieDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDtoObj, MovieDto.class);
    }
}
