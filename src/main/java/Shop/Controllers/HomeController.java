package Shop.Controllers;

import Shop.DTO.CategoryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class HomeController {
    private static List<CategoryDTO> list = new ArrayList<CategoryDTO>();

    @PostMapping("/add")
    public void add(@RequestBody CategoryDTO model) {
        list.add(model);
    }

    @GetMapping("/")
    public List<CategoryDTO> index() {
        return list;
    }

    private CategoryDTO findById(int id) {
        for (CategoryDTO dt : list
        ) {
            if (dt.getId() == id) {
                return dt;
            }
        }
        return null;
    }

    @DeleteMapping("/categories/{id}")
    private void CategoryDTO(@PathVariable("id") int category) {

        CategoryDTO dto = findById(category);
        if (dto != null) {
            list.remove(dto);

        }
    }
    @PutMapping("/edit/{id}")
    public CategoryDTO showUpdateForm(@PathVariable("id") int id, CategoryDTO model) {
        CategoryDTO dto = findById(id);
        dto.setName(model.getName());
        return dto;
    }
}
