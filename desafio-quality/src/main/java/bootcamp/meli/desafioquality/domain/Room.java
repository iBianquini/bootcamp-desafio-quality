package bootcamp.meli.desafioquality.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Room {
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres")
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^[A-Z][a-z]+", message = "O nome da Room deve comecar com uma letra maiuscula")
    private String name;

    @Range(max = 25, message = "A largura máxima permitida por comodo é de 25 metros")
    @NotNull(message = "A largura do comodo não pode estar vazia")
    private Double width;

    @Range(max = 33, message = "O comprimento maximo permitido por comodo é de 33 metros")
    @NotNull(message = "O comprimento do comodo não pode estar vazia")
    private Double length;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Room(String name, double width, double length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public Room() {
    }

    public double calculateArea() {
        return this.length * this.width;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
