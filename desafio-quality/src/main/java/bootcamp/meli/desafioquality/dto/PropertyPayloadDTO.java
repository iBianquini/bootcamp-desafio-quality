package bootcamp.meli.desafioquality.dto;


import bootcamp.meli.desafioquality.domain.District;
import bootcamp.meli.desafioquality.domain.Property;
import bootcamp.meli.desafioquality.domain.Room;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class PropertyPayloadDTO {
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres")
    @NotBlank(message = "O da propriedade não pode estar vazio")
    @Pattern(regexp = "^[A-Z][a-z]+", message = "O nome da propriedade deve comecar com uma letra maiuscula")
    private String name;

    @JsonProperty("district_id")
    private Long districtId;

    @NotEmpty(message = "Uma propriedade deve tem ao menos um comodo")
    private List<Room> rooms;

    public PropertyPayloadDTO() {
    }

    public PropertyPayloadDTO(String name, Long districtId, List<Room> rooms) {
        this.name = name;
        this.districtId = districtId;
        this.rooms = rooms;
    }

    public Property castToEntity() {
        return new Property(this.name, new District(this.districtId), this.rooms);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
