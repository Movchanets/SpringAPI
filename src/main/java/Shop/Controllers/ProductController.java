
package Shop.Controllers;



import Shop.DTO.product.CreateProductDTO;
import Shop.DTO.product.ProductItemDTO;
import Shop.DTO.product.UpdateProductDTO;
import Shop.entities.ProductEntity;

import Shop.mapper.ProductImagesMapper;
import Shop.mapper.ProductMapper;
import Shop.repositories.ProductImage;
import Shop.repositories.ProductImagesRepository;
import Shop.repositories.ProductRepository;
import Shop.storage.StorageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import Shop.repositories.CategoryRepository;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final StorageService storageService;
    private final ProductMapper productMapper;
    private final ProductImagesMapper productImagesMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImagesRepository productImagesRepository;
    @GetMapping("/get")
    public ResponseEntity<List<ProductItemDTO>> index() {
        var list = productMapper.productItemDTObyProducts(productRepository.findAll());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<ProductItemDTO> create(@Valid @RequestBody CreateProductDTO model) {
        ProductEntity product = new ProductEntity();
        product.setName(model.getName());
        product.setDescription(model.getDescription());
        product.setPrice(model.getPrice());

        if (model.getBase64() != null) {


            for (String s : model.getBase64()
            ) {
                String filename = storageService.save(s);
                productImagesRepository.save(new ProductImage(filename, product));
            }


            productRepository.save(product);
            return new ResponseEntity<>(productMapper.productItemDTOByProducts(product), HttpStatus.OK);

        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> delete(@PathVariable("id") int id) {

        var list = productRepository.getById(id).getProductImages();
        for (ProductImage productImage : list
        ) {
            storageService.delete(productImage.getUrlImage());
        }

        productRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductItemDTO> get(@PathVariable("id") int id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isPresent()) {
            return new ResponseEntity<>(productMapper.productItemDTOByProducts(productEntityOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")

    public ResponseEntity<ProductItemDTO> update(@PathVariable("id") int id,
                                                 @Valid @RequestBody UpdateProductDTO productDTO) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);

        if (productEntityOptional.isPresent()) {
            ProductEntity _product = productEntityOptional.get();
            _product.setName(productDTO.getName());
            _product.setDescription(productDTO.getDescription());
            _product.setPrice(productDTO.getPrice());
            if (productDTO.getCategoryId() != _product.getCategory().getId()) {
                if (categoryRepository.findById(productDTO.getCategoryId()).isPresent()) {
                    _product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).get());
                }
                if (productDTO.getBase64() != null) {
                    for (ProductImage productImage : _product.getProductImages()
                    ) {
                        storageService.delete(productImage.getUrlImage());
                        _product.getProductImages().clear();
                        for (String s : productDTO.getBase64()
                        ) {
                            String filename = storageService.save(s);
                            _product.getProductImages().add(new ProductImage(filename, _product));
                        }
                    }
                    return new ResponseEntity<>(productMapper.productItemDTOByProducts(productRepository.save(_product)), HttpStatus.OK);

                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}