package com.site.plazam.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Movie {

    @Id
    @Indexed(unique = true)
    private String id;

    @NotNull
    @NotEmpty
    private Map<String, String> name = new HashMap<>();

    private Map<String, String> surname = new HashMap<>();

    @NotNull
    @NotEmpty
    private Map<String, String> description = new HashMap<>();

    @NotNull
    private Integer duration;

    @NotNull
    @Field(name = "release_date")
    private LocalDate releaseDate;

    @NotNull
    @Field(name = "users_rating")
    private Float usersRating = 0F;

    @NotNull
    @Field(name = "mpaa_rating")
    private MPAA mpaaRating;

    @NotNull
    @Field(name = "imdb_rating")
    private Float imdbRating = 0F;

    @NotNull
    @NotEmpty
    @Field(name = "movie_language")
    private Map<String, String> movieLang = new HashMap<>();

    @NotNull
    @NotEmpty
    @Field(name = "movie_country")
    private Map<String, String> movieCountry = new HashMap<>();

    @Field(name = "movie_picture_id")
    private String widePictureId;

    @Field(name = "movie_poster_id")
    private String posterPictureId;

    @Field(name = "movie_gallery_ids")
    private List<String> galleryPictureIds = new ArrayList<>();

    @NotNull
    @NotEmpty
    @Field(name = "actor_ids")
    private List<String> actorIds = new ArrayList<>();

    @NotNull
    @NotEmpty
    private List<Genre> genres = new ArrayList<>();
}