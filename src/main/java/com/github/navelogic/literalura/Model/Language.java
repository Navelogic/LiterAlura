package com.github.navelogic.literalura.Model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Language {
    ENGLISH("en", "Inglês"),
    SPANISH("es", "Espanhol"),
    FRENCH("fr", "Francês"),
    PORTUGUESE("pt", "Português");

    private final String code;
    private final String fullName;

    Language(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    public static Language fromString(String text) {
        return Arrays.stream(Language.values())
                .filter(lang -> lang.code.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhum idioma encontrado para o código: " + text));
    }

    public static Language fromFullName(String text) {
        return Arrays.stream(Language.values())
                .filter(lang -> lang.fullName.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhum idioma encontrado para o nome: " + text));
    }
}
