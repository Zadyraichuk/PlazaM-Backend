package com.site.plazam.repository;

import com.site.plazam.domain.Movie;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    @Query(value = "{" +
            "$or: [" +
            "{'name.en':/?0/}," +
            "{'name.ua':/?0/}," +
            "{'name.pl':/?0/}," +
            "{'surname.en':/?1/}," +
            "{'surname.ua':/?1/}," +
            "{'surname.pl':/?1/}" +
            "]" +
            "}")
    List<Movie> findByMovieName(String name, String surname);

    @Query(value = "{" +
            "$or: [" +
            "{'name.en':/?0/}," +
            "{'name.ua':/?0/}," +
            "{'name.pl':/?0/}," +
            "{'surname.en':/?1/}," +
            "{'surname.ua':/?1/}," +
            "{'surname.pl':/?1/}" +
            "]" +
            "}")
    Page<Movie> findByMovieName(String name, String surname, Pageable pageable);

    @NotNull
    Page<Movie> findAll(@NotNull Pageable pageable);
}