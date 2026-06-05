package ec.devsu.api.bank.infraestructure.out.persistence.projection.client;

import java.util.UUID;

public record ClientInfoProjection(UUID personId, String name, Short genreId, String description, Short age,
                                   String identification, String address, String phone, UUID clientId, Boolean status) {
}
