package Shop.Controllers;

import Shop.DTO.CategoryDTO;
import Shop.DTO.category.CreateCategoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import Shop.entities.CategoryEntity;
import Shop.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping("/get")
    public ResponseEntity<List<CategoryEntity>> index() {
        var list = categoryRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<CategoryEntity> create(@RequestBody CreateCategoryDTO model) {
        CategoryEntity category = new CategoryEntity();
        category.setName(model.getName());
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private void CategoryDTO(@PathVariable("id") int id) {


        categoryRepository.deleteById(id);


    }

    @PutMapping("/edit/{id}")

    public ResponseEntity<CategoryEntity> updateTutorial(@PathVariable("id") int id, @RequestBody CategoryDTO tutorial) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);

        if (categoryEntityOptional.isPresent()) {
            CategoryEntity _category = categoryEntityOptional.get();
            _category.setName(tutorial.getName());
            return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}