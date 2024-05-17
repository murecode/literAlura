package org.educational.libraryapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("id")
        long id,
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        List<AuthorData> autor,
        @JsonAlias("languages")
        List<String> idiomas,
        @JsonAlias("download_count")
        int descargas

) {}
