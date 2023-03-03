package Shop.Services;

import Shop.DTO.product.ProductImageDTO;
import Shop.entities.ProductEntity;
import Shop.interfaces.IProductImageService;
import Shop.mapper.ProductImagesMapper;
import Shop.repositories.ProductImage;
import Shop.repositories.ProductImagesRepository;
import Shop.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductImageService implements IProductImageService {

    private final ProductImagesRepository productImagesRepository;
    private final ProductImagesMapper productImagesMapper;
    private final StorageService storageService;

    @Override
    public ProductImage create(ProductImageDTO model, ProductEntity product) {
        try {
            String filename = storageService.save(model.getName());
            ProductImage r = new ProductImage();
            r.setProduct(product);
            r.setPriority(model.getPriority());
            r.setName(filename);
            return r;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }



    @Override
    public void delete(String name) {
        storageService.delete(name);

    }
}
