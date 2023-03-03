package Shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String urlImage;
    @Column(nullable = true)
    private String description;
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();
}
