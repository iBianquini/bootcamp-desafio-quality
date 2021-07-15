package bootcamp.meli.desafioquality.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Property {
    private String name;
    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;
    @OneToMany
    @JoinColumn(name = "property_id", referencedColumnName = "id")
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
