package Shop.Services;

import Shop.DTO.product.ProductImageDTO;
import Shop.entities.ProductEntity;
import Shop.interfaces.IProductImageService;
import Shop.mapper.ProductImagesMapper;
import Shop.repositories.ProductImage;
import Shop.repositories.ProductImageRepository;

import Shop.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductImageService implements IProductImageService {

    private final ProductImageRepository productImagesRepository;
    private final ProductImagesMapper productImagesMapper;
    private final StorageService storageService;




    @Override
    public void delete(String name) {
        storageService.delete(name);

    }
}
