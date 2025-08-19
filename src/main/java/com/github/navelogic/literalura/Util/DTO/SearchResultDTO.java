package com.github.navelogic.literalura.Util.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SearchResultDTO(
        @JsonAlias("count") Integer count,
        @JsonAlias("results") List<BookDTO> results
) {
}
