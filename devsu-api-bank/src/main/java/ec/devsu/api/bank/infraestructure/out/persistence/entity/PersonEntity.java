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
@Table(name = "Person", schema = "dbo")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "person_id", nullable = false)
    private UUID personId;

    private String name;

    @Column(name = "genre_id", nullable = false)
    private Short genreId;

    private Short age;

    private String identification;

    private String address;

    private String phone;
}
