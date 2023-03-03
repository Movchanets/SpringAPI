package Shop.interfaces;

import Shop.DTO.category.CategoryItemDTO;
import Shop.DTO.category.CreateCategoryDTO;
import Shop.DTO.category.UpdateCategoryDTO;
import Shop.DTO.product.ProductImageDTO;
import Shop.entities.ProductEntity;
import Shop.repositories.ProductImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductImageService {


        ProductImage create(ProductImageDTO model, ProductEntity product);
        void delete(String name);

}
