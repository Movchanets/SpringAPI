
package Shop.Controllers;



import Shop.DTO.product.CreateProductDTO;
import Shop.DTO.product.ProductItemDTO;
import Shop.DTO.product.UpdateProductDTO;
import Shop.Services.ProductService;
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
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity<List<ProductItemDTO>> index() {
        return new ResponseEntity<>(productService.get(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<ProductItemDTO> create(@Valid @RequestBody CreateProductDTO model) {
       try {
              var result = productService.create(model);
              return new ResponseEntity<>(result, HttpStatus.OK);
       }
      catch (Exception e)
      {
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            productService.delete(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductItemDTO> get(@PathVariable("id") int id) {
        try {
            var result = productService.getById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")

    public ResponseEntity<ProductItemDTO> update(@PathVariable("id") int id,
                                                 @Valid @RequestBody UpdateProductDTO productDTO) {
    return new ResponseEntity<>(productService.update(id, productDTO), HttpStatus.OK);
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