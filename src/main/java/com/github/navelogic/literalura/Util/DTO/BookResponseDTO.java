package com.github.navelogic.literalura.Util.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookResponseDTO {
    private Integer count;
    private List<BookResultDTO> results;
}
