
package Shop.repositories;

        import Shop.entities.CategoryEntity;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImage, Integer> {

}
