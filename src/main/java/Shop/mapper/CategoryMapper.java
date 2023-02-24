package Shop.mapper;

import Shop.DTO.category.CategoryItemDTO;
import Shop.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryItemDTO categoryItemDTOByCategory(CategoryEntity category);
    List<CategoryItemDTO> categoryItemDTObyCategories(List<CategoryEntity> category);
}
