package org.educational.libraryapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorData(
        @JsonAlias("name")
        String nombre,
        @JsonAlias("birth_year")
        int nacimiento,
        @JsonAlias("death_year")
        int muerte
) {}
