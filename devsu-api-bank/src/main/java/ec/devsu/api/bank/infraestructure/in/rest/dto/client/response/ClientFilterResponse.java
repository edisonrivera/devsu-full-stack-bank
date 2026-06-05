package ec.devsu.api.bank.infraestructure.in.rest.dto.client.response;

import ec.devsu.api.bank.infraestructure.out.persistence.projection.client.ClientInfoProjection;

import java.util.List;

public record ClientFilterResponse(List<ClientInfoProjection> clients, long totalClients) {
}
