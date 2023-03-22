package Shop.entities;

        import Shop.repositories.ProductImage;
        import jakarta.persistence.*;
        import jakarta.validation.constraints.Max;
        import jakarta.validation.constraints.Min;
        import jakarta.validation.constraints.Size;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.hibernate.annotations.OnDelete;
        import org.hibernate.annotations.OnDeleteAction;

        import java.util.Date;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private boolean isDelete;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true, length = 10000)
   // @Max(value = 10000, message = "Description must be less than 10000 characters")
    @Size(min = 10,max = 10000, message = "Description must be  more than 10 and less than 10000 characters")
    private String description;
    @Column(nullable = false)
    @Min(value = 0, message = "Price must be greater than 0")
    private double price;
    @OneToMany(mappedBy="product")
    private List<ProductImage> productImages;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CategoryEntity category ;
}
