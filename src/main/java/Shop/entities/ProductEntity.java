package Shop.entities;

        import Shop.repositories.ProductImage;
        import jakarta.persistence.*;
        import jakarta.validation.constraints.Min;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.util.ArrayList;
        import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    @Min(value = 0, message = "Price must be greater than 0")
    private double price;
    @OneToMany(targetEntity = ProductImage.class, mappedBy = "product" , cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();
    @ManyToOne(targetEntity = CategoryEntity.class)
    private CategoryEntity category;
}
