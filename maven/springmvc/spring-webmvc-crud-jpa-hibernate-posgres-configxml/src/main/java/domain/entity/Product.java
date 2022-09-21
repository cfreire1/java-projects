package domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * https://vladmihalcea.com/how-to-fix-wrong-column-type-encountered-schema-validation-errors-with-jpa-and-hibernate/
 * https://vladmihalcea.com/jpa-entity-identifier-sequence/
 * https://stackoverflow.com/questions/54171473/how-can-i-get-an-input-date-from-a-form-in-a-controller-in-spring
 *
 */
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hibernate_sequence"
    )
    @SequenceGenerator(
            name = "hibernate_sequence",
            allocationSize = 1
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price",columnDefinition = "NUMERIC(15,2)")
    private float price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "create_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_at;

   }
