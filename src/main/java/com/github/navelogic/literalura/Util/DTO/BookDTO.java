package com.github.navelogic.literalura.Util.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorDTO> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Integer downloadCount
) {
}
