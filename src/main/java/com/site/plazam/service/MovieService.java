package com.site.plazam.service;

import com.site.plazam.domain.Genre;
import com.site.plazam.domain.Technology;
import com.site.plazam.dto.*;
import com.site.plazam.dto.parents.ActorSimpleDTO;
import com.site.plazam.dto.parents.MovieSimpleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {

    MovieFullDTO save(MovieCreateDTO movieCreateDTO);

    MovieForComingSoonDTO findMovieForComingSoonById(String id);

    MovieForCommentDTO findMovieForCommentById(String id);

    MovieForHomeSliderDTO findMovieForHomeSliderById(String id);

    MovieForMoviesListDTO findMovieForMoviesListById(String id);

    MovieForSeanceDTO findMovieForSeanceById(String id);

    MovieForTicketDTO findMovieForTicketById(String id);

    MovieForResultListDTO findMovieForResultListById(String id);

    MovieCreateDTO findMovieCreateById(String id);

    MovieFullDTO findMovieFullById(String id);


    List<MovieForComingSoonDTO> findMovieForComingSoonAll();

    List<MovieForHomeSliderDTO> findMovieForHomeSliderAll();

    List<MovieForMoviesListDTO> findMovieForMoviesListAll();

    Page<MovieForMoviesListDTO> findMovieForMoviesListAll(Pageable pageable);

    List<MovieForSeanceDTO> findMovieForSeanceByGenresIsContaining(List<Genre> genres);

    Page<MovieForSeanceDTO> findMovieForSeanceByGenresIsContaining(List<Genre> genres, Pageable pageable);


    List<MovieForMoviesListDTO> findMovieForMoviesListByMovieName(String fullName);

    List<MovieForResultListDTO> findMovieForResultListByMovieName(String fullName);

    Page<MovieForMoviesListDTO> findMovieForMoviesListByMovieName(String fullName,
                                                                  Pageable pageable);

    List<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateAfter(LocalDate date);

    List<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateBefore(LocalDate date);

    List<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateBetween(LocalDate from,
                                                                           LocalDate to);

    Page<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateAfter(LocalDate date,
                                                                         Pageable pageable);

    Page<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateBefore(LocalDate date,
                                                                          Pageable pageable);

    Page<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateBetween(LocalDate from,
                                                                           LocalDate to,
                                                                           Pageable pageable);

    Page<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateAfterAndGenresMatches(LocalDate date,
                                                                                         List<Genre> genres,
                                                                                         Pageable pageable);

    Page<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateBeforeAndGenresMatches(LocalDate date,
                                                                                          List<Genre> genres,
                                                                                          Pageable pageable);

    Page<MovieForMoviesListDTO> findMovieForMoviesListByReleaseDateBetweenAndGenresMatches(LocalDate from,
                                                                                           LocalDate to,
                                                                                           List<Genre> genres,
                                                                                           Pageable pageable);

    Page<MovieForMoviesListDTO> findAllByGenresMatches(List<Genre> genres,
                                                       Pageable pageable);

    List<MovieForHomeSliderDTO> findMovieForHomeSlideByReleaseDateAfter(LocalDate date);

    List<MovieForComingSoonDTO> findMovieForComingSoonByReleaseDateAfter(LocalDate date);

    List<MovieForComingSoonDTO> findMovieForComingSoonByReleaseDateBefore(LocalDate date);

    List<MovieForComingSoonDTO> findMovieForComingSoonByReleaseDateBetween(LocalDate from,
                                                                           LocalDate to);

    List<MovieForHomeSliderDTO> findMovieForHomeSliderByReleaseDateBeforeOrderByReleaseDate(LocalDate date);

    List<MovieForComingSoonDTO> findMovieForComingSoonByReleaseDateAfterOrderByReleaseDate(LocalDate date);

//    List<MovieForHomeSliderDTO> findMovieForHomeSliderByLastSeances(LocalDate currentDate);

//    Page<MovieForMoviesListDTO> findMovieForMoviesListByUserFavourites()

    void removeActorFromMovies(ActorSimpleDTO actor);

    void addAvailableTechnology(MovieSimpleDTO movie, Technology technology);

    void removeAvailableTechnology(MovieSimpleDTO movie, Technology technology);

    void delete(MovieSimpleDTO movie);

    void deleteAll();
}
