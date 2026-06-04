package ec.devsu.api.bank.infraestructure.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Client", schema = "dbo")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    private String password;

    private Boolean status;

    @Column(name = "person_id", nullable = false)
    private UUID personId;
}
