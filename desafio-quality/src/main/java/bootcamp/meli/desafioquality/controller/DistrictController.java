package bootcamp.meli.desafioquality.controller;

import bootcamp.meli.desafioquality.domain.District;
import bootcamp.meli.desafioquality.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/quality")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    
    /** 
     * Creates a district based on Body param
     * @param district
     */
    @PostMapping("/district")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDistrict(@Valid @RequestBody District district){
        this.districtService.createDistrict(district);
    }

}
