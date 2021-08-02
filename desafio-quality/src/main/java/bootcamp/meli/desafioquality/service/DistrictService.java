package bootcamp.meli.desafioquality.service;

import bootcamp.meli.desafioquality.domain.District;
import bootcamp.meli.desafioquality.exception.advice.EntityNotFoundException;
import bootcamp.meli.desafioquality.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictService {


    private DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    
    /** 
     * @param id
     * @return District
     * @throws EntityNotFoundException if does not find an entity with corresponding Id
     */
    public District findDistrictById(long id) {
        Optional<District> district = this.districtRepository.findById(id);

        if (district.isPresent())
            return district.get();
        throw new EntityNotFoundException("Distrito não encontrado.");
    }


    
    /** 
     * Creates a district
     * @param district
     */
    public void createDistrict(District district) {
        this.districtRepository.save(district);
    }
}
