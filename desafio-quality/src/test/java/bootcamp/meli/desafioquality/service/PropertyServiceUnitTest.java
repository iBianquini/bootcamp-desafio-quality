package bootcamp.meli.desafioquality.service;

import bootcamp.meli.desafioquality.domain.District;
import bootcamp.meli.desafioquality.domain.Property;
import bootcamp.meli.desafioquality.domain.Room;
import bootcamp.meli.desafioquality.dto.PropertyPayloadDTO;
import bootcamp.meli.desafioquality.dto.PropertyValueDTO;
import bootcamp.meli.desafioquality.dto.RoomDTO;
import bootcamp.meli.desafioquality.exception.advice.EntityNotFoundException;
import bootcamp.meli.desafioquality.repository.PropertyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyServiceUnitTest {

    private PropertyService propertyService;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private DistrictService districtService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        propertyService = new PropertyService(propertyRepository, districtService);
    }

    @Test
    void should_createProperty_whenValidPayload(){
        PropertyPayloadDTO propertyPayloadDTO = new PropertyPayloadDTO("Propety", 1L,new ArrayList<>());
        Mockito.when(districtService.findDistrictById(propertyPayloadDTO.getDistrictId())).thenReturn(new District("Distrito 01", BigDecimal.valueOf(30)));

        propertyService.createProperty(propertyPayloadDTO);

        Mockito.verify(propertyRepository).save(Mockito.any());
    }

    @Test
    void should_throwException_createProperty_whenInValidPayloadDueToName(){
        PropertyPayloadDTO propertyPayloadDTO = new PropertyPayloadDTO("propety", 1L,new ArrayList<>());
        Property property = propertyPayloadDTO.castToEntity();

        String errorMessage = "Distrito não encontrado.";

        Mockito.when(districtService.findDistrictById(propertyPayloadDTO.getDistrictId())).thenThrow(new EntityNotFoundException(errorMessage));

        Exception ex = assertThrows(EntityNotFoundException.class,
                () -> propertyService.createProperty(propertyPayloadDTO)
        );

        assertEquals(errorMessage, ex.getMessage());
    }

    @Test
    void should_calculatePropertyValue_whenIdProperty(){
        long id = 1L;
        District district = new District("district",BigDecimal.valueOf(10));

        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Sala", 20, 10));
        rooms.add(new Room("Quarto", 23, 15));

        Property property = new Property("Property",district,rooms);
        property.setDistrict(district);

        PropertyValueDTO propertyValueDTO = new PropertyValueDTO(property.getName(), property.calculatePropertyValue());

        Mockito.when(propertyRepository.findById(id)).thenReturn( Optional.of(property) );

        assertEquals(propertyValueDTO.getValue(), propertyService.calculatePropertyValue(id).getValue());
        assertEquals(propertyValueDTO.getName(), propertyService.calculatePropertyValue(id).getName());

    }

    @Test
    void should_throwException_calculatePropertyValue_whenIdProperty(){
        long id = 1L;
        District district = new District("district",BigDecimal.valueOf(10));

        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Sala", 20, 10));
        rooms.add(new Room("Quarto", 23, 15));

        Property property = new Property("Property",district,rooms);
        property.setDistrict(district);

        PropertyValueDTO propertyValueDTO = new PropertyValueDTO(property.getName(), property.calculatePropertyValue());

        String errorMessage = "Property não encontrada.";

        Mockito.when(propertyRepository.findById(id)).thenThrow(new EntityNotFoundException(errorMessage));

        Exception ex = assertThrows(EntityNotFoundException.class,
                () -> propertyService.calculatePropertyValue(id)
        );

        assertEquals(errorMessage, ex.getMessage());

    }

    @Test
    void should_getBiggestRoom_whenIdProperty(){
        long id = 1L;
        District district = new District("district",BigDecimal.valueOf(10));

        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Sala", 20, 10));
        rooms.add(new Room("Quarto", 23, 15));

        Property property = new Property("Property",district,rooms);
        property.setDistrict(district);

        RoomDTO roomDTO = new RoomDTO(property.getName(), property.getBiggestRoom().getName(), property.getBiggestRoom().calculateArea());

        Mockito.when(propertyRepository.findById(id)).thenReturn( Optional.of(property) );

        assertEquals(roomDTO.getRoomName(), propertyService.getBiggestRoom(id).getRoomName());
        assertEquals(roomDTO.getPropertyName(), propertyService.getBiggestRoom(id).getPropertyName());
    }

    @Test
    void should_getRoomsSize_whenIdPropety(){
        long id = 1L;
        District district = new District("district",BigDecimal.valueOf(10));

        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Sala", 20, 10));
        rooms.add(new Room("Quarto", 23, 15));

        Property property = new Property("Property",district,rooms);
        property.setDistrict(district);

        Mockito.when(propertyRepository.findById(id)).thenReturn( Optional.of(property) );

        assertEquals(property.getRoomsArea().size(), propertyService.getRoomsArea(id).size());
    }

}
