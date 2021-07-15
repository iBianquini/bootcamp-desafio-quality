package bootcamp.meli.desafioquality.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class District {
    @Size(max = 45)
    @NotBlank
    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 13, fraction = 2)
    private BigDecimal valueM2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public District(String name, BigDecimal valueM2) {
        this.name = name;
        this.valueM2 = valueM2;
    }

    public District() {
    }

    public District(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValueM2() {
        return valueM2;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
