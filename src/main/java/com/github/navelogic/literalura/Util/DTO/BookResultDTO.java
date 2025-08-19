package com.github.navelogic.literalura.Util.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookResultDTO {
    private String title;
    private List<AuthorResultDTO> authors;
    private List<String> languages;
    private List<String> subjects;
    private List<String> bookshelves;
    private boolean copyright;
    private int download_count;

    @Override
    public String toString() {
        return "BookResultDTO{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", subjects=" + subjects +
                ", bookshelves=" + bookshelves +
                ", copyright=" + copyright +
                ", download_count=" + download_count;
    }
}
