package Shop.Controllers;


import Shop.DTO.category.CategoryItemDTO;
import Shop.DTO.category.CreateCategoryDTO;
import Shop.DTO.category.UpdateCategoryDTO;
import Shop.Services.CategoryService;
import Shop.mapper.CategoryMapper;
import Shop.repositories.ProductImagesRepository;
import Shop.storage.StorageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import Shop.entities.CategoryEntity;
import Shop.repositories.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<List<CategoryItemDTO>> index() {
        return new ResponseEntity<>(categoryService.get(), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<CategoryItemDTO> create(@Valid @RequestBody CreateCategoryDTO model) {
        var result = categoryService.create(model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryItemDTO> getCategoryById(@PathVariable("id") int categoryId) {
        var category = categoryService.getById(categoryId);
        if(category!=null)
        {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryItemDTO> update(@PathVariable("id") int categoryId,
                                                  @RequestBody UpdateCategoryDTO model) {
        var category = categoryService.update(categoryId, model);
        if(category!=null)
        {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity<>("Катагорія знищена.", HttpStatus.OK);
    }
//    private final StorageService storageService;
//    private final CategoryMapper categoryMapper;
//    private final CategoryRepository categoryRepository;
//
//
//    @GetMapping("/get")
//    public ResponseEntity<List<CategoryItemDTO>> index() {
//        var list = categoryMapper.categoryItemDTObyCategories(categoryRepository.findAll());
//
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @PostMapping("create")
//    public ResponseEntity<CategoryItemDTO> create(@Valid @RequestBody CreateCategoryDTO model) {
//        CategoryEntity category = new CategoryEntity();
//        category.setName(model.getName());
//        category.setDescription(model.getDescription());
//
//        if (model.getBase64() != null||model.getBase64() != "") {
//
//            String filename = storageService.save(model.getBase64());
//            category.setUrlImage(filename);
//        }
//
//
//        categoryRepository.save(category);
//        return new ResponseEntity<>(categoryMapper.categoryItemDTOByCategory(category), HttpStatus.OK);
//
//    }
//
//    @DeleteMapping("/delete/{id}")
//    private ResponseEntity<String> CategoryDTO(@PathVariable("id") int id) {
//
//        String name = categoryRepository.getById(id).getUrlImage();
//        if (name != null) {
//            storageService.delete(name);
//        }
//        categoryRepository.deleteById(id);
//        return new ResponseEntity<>("Deleted", HttpStatus.OK);
//
//    }
//
//    @GetMapping("/get/{id}")
//    public ResponseEntity<CategoryItemDTO> get(@PathVariable("id") int id) {
//        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);
//        if (categoryEntityOptional.isPresent()) {
//            return new ResponseEntity<>(categoryMapper.categoryItemDTOByCategory(categoryEntityOptional.get()), HttpStatus.OK);
//             } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/edit/{id}")
//
//    public ResponseEntity<CategoryItemDTO> update(@PathVariable("id") int id,@Valid @RequestBody UpdateCategoryDTO categoryDTO) {
//        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);
//
//        if (categoryEntityOptional.isPresent()) {
//            CategoryEntity _category = categoryEntityOptional.get();
//            _category.setName(categoryDTO.getName());
//            _category.setDescription(categoryDTO.getDescription());
//            if (categoryDTO.getBase64()  != null&&categoryDTO.getBase64() != "") {
//                    if(_category.getUrlImage() != null){
//                        storageService.delete(_category.getUrlImage());
//                    }
//                String filename = storageService.save(categoryDTO.getBase64());
//                _category.setUrlImage(filename);
//            }
//            return new ResponseEntity<>(categoryMapper.categoryItemDTOByCategory(categoryRepository.save(_category)), HttpStatus.OK);
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
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