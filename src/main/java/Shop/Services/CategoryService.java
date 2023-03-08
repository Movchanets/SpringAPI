
package Shop.Services;

        import Shop.DTO.category.CategoryItemDTO;
        import Shop.DTO.category.CreateCategoryDTO;
        import Shop.DTO.category.UpdateCategoryDTO;
        import Shop.entities.CategoryEntity;
        import Shop.interfaces.ICategoryService;
        import Shop.mapper.CategoryMapper;
        import Shop.repositories.CategoryRepository;
        import Shop.storage.StorageService;
        import lombok.AllArgsConstructor;

        import org.springframework.stereotype.Service;


        import java.util.List;
        import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;
    @Override
    public List<CategoryItemDTO> get() {
        var data = categoryRepository.findAll();
        var list = categoryMapper.categoryItemDTObyCategories(data);
        return list;
    }

    @Override
    public CategoryItemDTO create(CreateCategoryDTO model) {
        var file = storageService.saveMultipartFile(model.getFile());
        var category = categoryMapper.categoryEntityByCategoryCreateDTO(model);
        category.setUrlImage(file);
        categoryRepository.save(category);
        return categoryMapper.categoryItemDTOByCategory(category);
    }

    @Override
    public CategoryItemDTO getById(int id) {
        var categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent())
        {
            var data = categoryMapper.categoryItemDTOByCategory(categoryOptional.get());
            return data;
        }
        return null;
    }

    @Override
    public CategoryItemDTO getByName(String name) {
      var list = categoryRepository.findAll();
      if(list.size()>0)
      {
          for (CategoryEntity category: list) {
              if(category.getName().equals(name))
              {
                  return categoryMapper.categoryItemDTOByCategory(category);
              }
          }
      }
        return null;

    }

    @Override
    public CategoryItemDTO update(int id, UpdateCategoryDTO model) {
        var categoryOptinal = categoryRepository.findById(id);
        if(categoryOptinal.isPresent())
        {
            var category = categoryOptinal.get();
            category.setName(model.getName());
            categoryRepository.save(category);
            var result = categoryMapper.categoryItemDTOByCategory(category);
            return result;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        CategoryEntity category = categoryRepository.findById(id).get();
        storageService.delete(category.getUrlImage());
        categoryRepository.deleteById(id);
    }
}