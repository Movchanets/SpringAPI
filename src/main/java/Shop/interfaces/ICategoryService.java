
package Shop.interfaces;


        import Shop.DTO.category.CategoryItemDTO;
        import Shop.DTO.category.CreateCategoryDTO;
        import Shop.DTO.category.UpdateCategoryDTO;

        import java.util.List;

public interface ICategoryService {
    List<CategoryItemDTO> get();
    CategoryItemDTO create(CreateCategoryDTO model);
    CategoryItemDTO getById(int id);
    CategoryItemDTO getByName(String name);
    CategoryItemDTO update(int id, UpdateCategoryDTO model);
    void delete(int id);
}