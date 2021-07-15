package bootcamp.meli.desafioquality.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class District {
    private String name;
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
