package Shop.Services;

import Shop.DTO.product.CreateProductDTO;
import Shop.DTO.product.ProductImageDTO;
import Shop.DTO.product.ProductItemDTO;
import Shop.DTO.product.UpdateProductDTO;
import Shop.entities.ProductEntity;
import Shop.interfaces.IProductService;
import Shop.mapper.CategoryMapper;
import Shop.mapper.ProductMapper;
import Shop.repositories.CategoryRepository;
import Shop.repositories.ProductImage;
import Shop.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;
    private final ProductImageService productImageService;
    @Override
    public List<ProductItemDTO> get() {
        return productMapper.productItemDTObyProducts(productRepository.findAll());

            }

    @Override
    public ProductItemDTO create(CreateProductDTO model) {
        ProductEntity product = new ProductEntity();
        product.setName(model.getName());
        product.setDescription(model.getDescription());
        product.setPrice(model.getPrice());
        var category = categoryRepository.findById(model.getCategoryId()).get();
        if(category != null)
        {
       product.setCategory(category);
       category.getProducts().add(product);
        }
        else {
            return null;
        }
        if(model.getBase64() != null)
        {
            List<ProductImage> productImages = new ArrayList<>();
            for (ProductImageDTO s : model.getBase64()
            ) {
              productImages.add(  productImageService.create(s, product));
            }
            product.setProductImages(productImages);
        }
        productRepository.save(product);
        return productMapper.productItemDTOByProducts(product);
    }

    @Override
    public ProductItemDTO getById(int id) {
        return productMapper.productItemDTOByProducts(productRepository.findById(id).get());
    }

    @Override
    public ProductItemDTO update(int id, UpdateProductDTO model) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
