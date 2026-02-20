package ee.eljas.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean active;
    private Integer stock;

    @ManyToOne
    private Category category;


    //Andmebaasi, aga ei määra seda väärtust:
    //double - 0
    //boolean - false
    //int - 0

    //Andmebaasi, aga ei määra seda väärtust:
    //Double - null
    //Boolean - null
    //Integer - null
}
