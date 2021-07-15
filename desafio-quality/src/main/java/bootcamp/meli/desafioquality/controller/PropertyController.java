package bootcamp.meli.desafioquality.controller;

import bootcamp.meli.desafioquality.domain.Property;
import bootcamp.meli.desafioquality.dto.PropertyPayloadDTO;
import bootcamp.meli.desafioquality.dto.PropertyValueDTO;
import bootcamp.meli.desafioquality.dto.RoomAreaDTO;
import bootcamp.meli.desafioquality.dto.RoomDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quality")
public class PropertyController {



    @PostMapping("/property")
    public ResponseEntity<Property> postProperty(@Valid @RequestBody PropertyPayloadDTO propertyPayloadDTO) {
        Property property = new Property();
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @GetMapping("/property/{propertyId}/calculate-value")
    public ResponseEntity<PropertyValueDTO> calculatePropertyValue(@PathVariable int propertyId) {
        PropertyValueDTO propertyValueDTO = new PropertyValueDTO();
        return new ResponseEntity<>(propertyValueDTO, HttpStatus.CREATED);
    }

    @GetMapping("/property/{propertyId}/bigger-room")
    public ResponseEntity<RoomDTO> getBiggerRoom(@PathVariable int propertyId) {
        RoomDTO roomDTO = new RoomDTO();
        return new ResponseEntity<>(roomDTO, HttpStatus.CREATED);
    }

    @GetMapping("/property/{propertyId}/rooms-size")
    public ResponseEntity<List<RoomAreaDTO>> getRoomsSizeList(@PathVariable int propertyId) {
        List<RoomAreaDTO> roomsAreaDTO = new ArrayList<>();
        return new ResponseEntity<>(roomsAreaDTO, HttpStatus.CREATED);
    }

}
