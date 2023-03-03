package Shop.repositories;

import Shop.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 5000, nullable = false)
    private String name;
    private int priority;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private ProductEntity product;
    private boolean isDelete;}

