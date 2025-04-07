package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.entity.Movie;
import com.example.repository.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> searchMovies(String query, int page, int size) {
        var pageable = PageRequest.of(page, size);
        return movieRepository.searchByQuery(query, pageable);
    }
}