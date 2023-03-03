
package Shop.mapper;

import Shop.DTO.category.CategoryItemDTO;
import Shop.DTO.product.ProductImageDTO;
import Shop.DTO.product.ProductItemDTO;
import Shop.entities.CategoryEntity;
import Shop.repositories.ProductImage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImagesMapper {
    ProductImageDTO categoryItemDTOByCategory(ProductImage image);

    List<ProductImageDTO> categoryItemDTObyCategories(List<ProductImage> images);
}
