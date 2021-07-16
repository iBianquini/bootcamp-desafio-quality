package bootcamp.meli.desafioquality.controller;

import bootcamp.meli.desafioquality.domain.District;
import bootcamp.meli.desafioquality.domain.Property;
import bootcamp.meli.desafioquality.domain.Room;
import bootcamp.meli.desafioquality.dto.PropertyPayloadDTO;
import bootcamp.meli.desafioquality.dto.PropertyValueDTO;
import bootcamp.meli.desafioquality.repository.DistrictRepository;
import bootcamp.meli.desafioquality.repository.PropertyRepository;
import bootcamp.meli.desafioquality.service.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_createProperty_whenValidPayload() throws Exception {
        PropertyPayloadDTO dto = this.createValidPropertyPayloadDTO();
        String requestPayload = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/quality/property")
                .contentType("application/json")
                .content(requestPayload)).andExpect(status().isCreated());
    }

    @Test
    void should_notCreateProperty_whenInvalidPayload() throws Exception {
        PropertyPayloadDTO dto = this.createInvalidPayloadDTO();
        String requestPayload = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/quality/property")
                .contentType("application/json")
                .content(requestPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_calculatePropertyValue_whenValidProperty() throws Exception {
        // Arrange
        Property p = this.createValidPropertyPayloadDTO().castToEntity();
        Property persistedProperty = propertyRepository.save(p);
        // Action and Assert
        mockMvc.perform(get("/quality/property/" + persistedProperty.getId() + "/calculate-value"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(persistedProperty.getName())))
                .andExpect(jsonPath("$.value", is(6000.0)));
    }

    @Test
    void should_notCalculatePropertyValue_whenPropertyNotExists() throws Exception {
        long invalidId = 0;
        mockMvc.perform(get("/quality/property/" + invalidId + "/calculate-value"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_getBiggestRoom_whenValidProperty() throws Exception {
        Property p = this.createValidPropertyPayloadDTO().castToEntity();
        Room biggestRoom = new Room("Cozinha", 3.0, 3.0);
        p.addRoom(biggestRoom);
        Property persistedProperty = propertyRepository.save(p);

        mockMvc.perform(get("/quality/property/" + persistedProperty.getId() + "/biggest-room"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propertyName", is(persistedProperty.getName())))
                .andExpect(jsonPath("$.roomName", is(biggestRoom.getName())))
                .andExpect(jsonPath("$.area", is(9.0)));
    }

    @Test
    void should_notGetBiggestRoom_whenInvalidProperty() throws Exception {
        long invalidId = 0;
        mockMvc.perform(get("/quality/property/" + invalidId + "/biggest-room"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_getRoomsSize_whenValidProperty() throws Exception{
        Property p = this.createValidPropertyPayloadDTO().castToEntity();
        Room anotherRoom = new Room("Cozinha", 3.0, 3.0);
        p.addRoom(anotherRoom);
        Property persistedProperty = propertyRepository.save(p);

        mockMvc.perform(get("/quality/property/" + persistedProperty.getId() + "/rooms-size"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].roomName", is("Sala")))
                .andExpect(jsonPath("$.[0].area", is(6.0)))
                .andExpect(jsonPath("$.[1].roomName", is("Cozinha")))
                .andExpect(jsonPath("$.[1].area", is(9.0)));
    }

    @Test
    void should_notGetRoomsSize_whenInvalidProperty() throws Exception{
        long invalidId = 0;
        mockMvc.perform(get("/quality/property/" + invalidId + "/rooms-size"))
                .andExpect(status().isBadRequest());
    }

    private PropertyPayloadDTO createValidPropertyPayloadDTO() {
        District d = this.createAndPersistDistrict();
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Sala", 2.0, 3.0));
        return new PropertyPayloadDTO("Chacara", d.getId(), rooms);
    }

    private PropertyPayloadDTO createInvalidPayloadDTO() {
        return new PropertyPayloadDTO();
    }

    private District createAndPersistDistrict() {
        District district = new District("District", new BigDecimal("1000.0"));
        return districtRepository.save(district);
    }
}
