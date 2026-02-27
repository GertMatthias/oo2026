package ee.eljas.proovikontrolltoo.dto;

import ee.eljas.proovikontrolltoo.entity.FilmType;

public record FilmSaveDto(
        String title,
        FilmType type
) {
}
