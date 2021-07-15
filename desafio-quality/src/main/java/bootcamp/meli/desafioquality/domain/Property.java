package bootcamp.meli.desafioquality.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Property {
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres")
    @NotBlank(message = "O da propriedade não pode estar vazio")
    @Pattern(regexp = "^[A-Z][a-z]+", message = "O nome da propriedade deve comecar com uma letra maiuscula")
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    @NotEmpty(message = "Uma propriedade precisa de um distrito")
    private District district;

    @OneToMany
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @NotEmpty(message = "Uma propriedade deve tem ao menos um comodo")
    private List<Room> rooms;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Property(String name, District district, List<Room> rooms) {
        this.name = name;
        this.district = district;
        this.rooms = rooms;
    }

    public Property() {
    }

    public String getName() {
        return name;
    }

    public District getDistrict() {
        return district;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
