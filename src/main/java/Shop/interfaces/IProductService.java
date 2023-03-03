package Shop.interfaces;

import Shop.DTO.category.CategoryItemDTO;
import Shop.DTO.category.CreateCategoryDTO;
import Shop.DTO.category.UpdateCategoryDTO;
import Shop.DTO.product.CreateProductDTO;
import Shop.DTO.product.ProductItemDTO;
import Shop.DTO.product.UpdateProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductItemDTO> get();
    ProductItemDTO create(CreateProductDTO model);
    ProductItemDTO getById(int id);
    ProductItemDTO update(int id, UpdateProductDTO model);
    void delete(int id);
}
