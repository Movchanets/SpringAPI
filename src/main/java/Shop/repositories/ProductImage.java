package Shop.repositories;

import Shop.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String urlImage;
    @ManyToOne(targetEntity = ProductEntity.class)
    private ProductEntity product;
    public ProductImage (String urlImage, ProductEntity product){
        this.urlImage = urlImage;
    }
}
