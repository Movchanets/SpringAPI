package Shop.DTO.category;

import lombok.Data;

@Data
public class CategoryItemDTO {
    private  int id;
    private  String name;
    private  String urlImage;
    private  String description;

    public CategoryItemDTO() {

    }

    public CategoryItemDTO(int id, String name, String urlImage, String description) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.description = description;
    }
}
