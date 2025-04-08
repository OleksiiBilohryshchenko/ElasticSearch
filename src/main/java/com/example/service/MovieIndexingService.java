package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Movie;
import com.example.entity.MovieDoc;
import com.example.repository.MovieElasticsearchRepository;
import com.example.repository.MovieRepository;

import java.util.List;

@Service
public class MovieIndexingService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieElasticsearchRepository movieElasticsearchRepository;

    @Transactional(readOnly = true)
    public void reindexAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        movieElasticsearchRepository.saveAll(
                movies.stream().map(
                        movie -> new MovieDoc(
                                movie.getId(),
                                movie.getMovie(),
                                movie.getOverview()
                        )
                ).toList()
        );
    }
}
