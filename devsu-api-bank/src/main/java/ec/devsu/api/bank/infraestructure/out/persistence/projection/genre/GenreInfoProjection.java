package ec.devsu.api.bank.infraestructure.out.persistence.projection.genre;

import ec.devsu.api.bank.domain.enums.GenreEnum;

public record GenreInfoProjection(Short genreId, GenreEnum mnemonic) {
}
