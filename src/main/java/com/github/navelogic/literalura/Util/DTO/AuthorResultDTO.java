package com.github.navelogic.literalura.Util.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResultDTO {
    private String name;
    private Integer birth_year;
    private Integer death_year;
}
