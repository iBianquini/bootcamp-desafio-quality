package bootcamp.meli.desafioquality.controller;

import bootcamp.meli.desafioquality.domain.Property;
import bootcamp.meli.desafioquality.dto.PropertyPayloadDTO;
import bootcamp.meli.desafioquality.dto.PropertyValueDTO;
import bootcamp.meli.desafioquality.dto.RoomAreaDTO;
import bootcamp.meli.desafioquality.dto.RoomDTO;
import bootcamp.meli.desafioquality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/quality")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/property")
    public ResponseEntity<Void> postProperty(@Valid @RequestBody PropertyPayloadDTO propertyPayloadDTO) {
        Property property = new Property();
        propertyService.createProperty(propertyPayloadDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/property/{propertyId}/calculate-value")
    public ResponseEntity<PropertyValueDTO> calculatePropertyValue(@PathVariable long propertyId) {
        PropertyValueDTO propertyValueDTO = propertyService.calculatePropertyValue(propertyId);
        return new ResponseEntity<>(propertyValueDTO, HttpStatus.OK);
    }

    @GetMapping("/property/{propertyId}/biggest-room")
    public ResponseEntity<RoomDTO> getBiggestRoom(@PathVariable int propertyId) {
        RoomDTO roomDTO = propertyService.getBiggestRoom(propertyId);
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping("/property/{propertyId}/rooms-size")
    public ResponseEntity<List<RoomAreaDTO>> getRoomsSizeList(@PathVariable long propertyId) {
        List<RoomAreaDTO> roomsAreaDTO = propertyService.getRoomsArea(propertyId);
        return new ResponseEntity<>(roomsAreaDTO, HttpStatus.OK);
    }

}
