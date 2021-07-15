package bootcamp.meli.desafioquality.service;

import bootcamp.meli.desafioquality.domain.District;
import bootcamp.meli.desafioquality.domain.Property;
import bootcamp.meli.desafioquality.domain.Room;
import bootcamp.meli.desafioquality.dto.PropertyPayloadDTO;
import bootcamp.meli.desafioquality.dto.PropertyValueDTO;
import bootcamp.meli.desafioquality.dto.RoomAreaDTO;
import bootcamp.meli.desafioquality.dto.RoomDTO;
import bootcamp.meli.desafioquality.exception.advice.EntityNotFoundException;
import bootcamp.meli.desafioquality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {


    private PropertyRepository repository;

    private DistrictService districtService;

    @Autowired
    public PropertyService(PropertyRepository repository,DistrictService districtService){
        this.repository = repository;
        this.districtService = districtService;
    }

    public void createProperty(PropertyPayloadDTO payloadDTO) {
        Property property = payloadDTO.castToEntity();

        District district = this.districtService.findDistrictById(payloadDTO.getDistrictId());

        property.setDistrict(district);

        repository.save(property);
    }

    public PropertyValueDTO calculatePropertyValue(long propertyId) {
        Property property = this.findById(propertyId);

        return new PropertyValueDTO(property.getName(), property.calculatePropertyValue());
    }

    public RoomDTO getBiggestRoom(long propertyId) {
        Property property = this.findById(propertyId);

        Room biggestRoom = property.getBiggestRoom();

        return new RoomDTO(property.getName(), biggestRoom.getName(), biggestRoom.calculateArea());
    }

    public List<RoomAreaDTO> getRoomsArea(long propertyId) {
        Property property = this.findById(propertyId);

        return property.getRoomsArea();
    }

    private Property findById(long id) {
        Optional<Property> property = repository.findById(id);

        if (property.isPresent())
            return property.get();

        throw new EntityNotFoundException("Property não encontrada.");
    }

}
