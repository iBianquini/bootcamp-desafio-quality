package bootcamp.meli.desafioquality.service;

import bootcamp.meli.desafioquality.domain.District;
import bootcamp.meli.desafioquality.exception.advice.EntityNotFoundException;
import bootcamp.meli.desafioquality.repository.DistrictRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DistrictServiceUnitTest {

    private DistrictService service;

    private DistrictRepository repository;

    @BeforeEach
    public void init() {
        repository = Mockito.mock(DistrictRepository.class);
        service = new DistrictService(repository);
    }


    @Test
    public void should_throwException_whenDistrictNotExists() {
        //Arrange
        long id = 1;
        String errorMessage = "Distrito não encontrado.";

        Optional<District> district = Optional.empty();

        Mockito.when(repository.findById(id)).thenReturn(district);

        //Action
        Exception ex = assertThrows(EntityNotFoundException.class, () -> {
            service.findDistrictById(id);
        });


        //Assert
        assertEquals(errorMessage, ex.getMessage());
    }

    @Test
    public void should_returnDistrict_whenValidId() {
        //Arrange
        long id = 1L;

        District district = new District("Bairro1", new BigDecimal(25));

        Optional<District> opt = Optional.of(district);

        Mockito.when(repository.findById(id)).thenReturn(opt);

        //Action
        var response = service.findDistrictById(id);

        //Assert
        assertEquals(response,district);
    }

}
