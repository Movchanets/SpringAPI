package Shop.Services;

import Shop.DTO.product.CreateProductDTO;
import Shop.DTO.product.ProductImageDTO;
import Shop.DTO.product.ProductItemDTO;
import Shop.DTO.product.UpdateProductDTO;
import Shop.interfaces.IProductService;
import Shop.mapper.ProductMapper;
import Shop.repositories.ProductImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import Shop.entities.CategoryEntity;
import Shop.entities.ProductEntity;

import Shop.repositories.ProductImageRepository;
import Shop.repositories.ProductRepository;
import Shop.storage.StorageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final StorageService storageService;
    private final ProductMapper productMapper;

    @Override
    public List<ProductItemDTO> get() {
        var products = productRepository.findAll();
        var result = new ArrayList<ProductItemDTO>();
        for (var p:products) {
            var item = productMapper.productItemDTOByProducts(p);
            for(var img : p.getProductImages())
                item.getFiles().add(img.getName());
            result.add(item);
        }
        return result;
    }

    @Override
    public ProductItemDTO create(CreateProductDTO model) {
        var p = new ProductEntity();
        var cat = new CategoryEntity();
        cat.setId(model.getCategoryId());
        p.setName(model.getName());
        p.setDescription(model.getDescription());
        p.setPrice(model.getPrice());
        p.setDateCreated(new Date());
        p.setCategory(cat);
        p.setDelete(false);
        productRepository.save(p);
        int priority=1;
        for (var img : model.getFiles()) {
            var file = storageService.saveMultipartFile(img);
            ProductImage pi = new ProductImage();
            pi.setName(file);
            pi.setDateCreated(new Date());
            pi.setPriority(priority);
            pi.setDelete(false);
            pi.setProduct(p);
            productImageRepository.save(pi);
            priority++;
        }
        return null;
    }

    @Override
    public ProductItemDTO getById(int id) {
        return null;
    }

    @Override
    public ProductItemDTO update(int id, UpdateProductDTO model) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}