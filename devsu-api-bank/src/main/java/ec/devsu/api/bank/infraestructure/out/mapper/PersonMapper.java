package ec.devsu.api.bank.infraestructure.out.mapper;

import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
import ec.devsu.api.bank.infraestructure.out.persistence.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(source = "genre", target = "genreId")
    PersonEntity toPersonEntity(final ClientRequest clientRequest);
}
