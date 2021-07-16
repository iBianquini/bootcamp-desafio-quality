package bootcamp.meli.desafioquality.domain;

import bootcamp.meli.desafioquality.dto.RoomAreaDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Property {
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres")
    @NotBlank(message = "O da propriedade não pode estar vazio")
    @Pattern(regexp = "^[A-Z][a-z]+", message = "O nome da propriedade deve comecar com uma letra maiuscula")
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    @NotNull(message = "Uma propriedade precisa de um distrito")
    private District district;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @NotNull(message = "Uma propriedade deve tem ao menos um comodo")
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

    public BigDecimal calculatePropertyValue() {
        return this.district.getValueM2().multiply(BigDecimal.valueOf(this.calculateTotalArea()));
    }

    public double calculateTotalArea() {
        return this.rooms.stream().map(Room::calculateArea).reduce(0.0, Double::sum);
    }

    public Room getBiggestRoom() {
        return this.rooms.stream().max(Comparator.comparing(Room::calculateArea)).orElse(null);
    }

    public List<RoomAreaDTO> getRoomsArea() {
        List<RoomAreaDTO> roomAreaDTOS = new ArrayList<>();

        for (Room room : this.rooms) {
            roomAreaDTOS.add(new RoomAreaDTO(room.getName(), room.calculateArea()));
        }

        return roomAreaDTOS;
    }

    public void addRoom (Room newRoom) {
        this.rooms.add(newRoom);
    }

    public void setDistrict(District district) {
        this.district = district;
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
