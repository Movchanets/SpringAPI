
package Shop.mapper;


import Shop.DTO.product.ProductItemDTO;
import Shop.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "category")
  ProductItemDTO productItemDTOByProducts(ProductEntity product);

   List<ProductItemDTO> productItemDTObyProducts(List<ProductEntity> product);
}
