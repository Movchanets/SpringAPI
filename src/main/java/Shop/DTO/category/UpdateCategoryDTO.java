package Shop.DTO.category;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateCategoryDTO {

    @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Nullable
    private String base64;
    @Length(min = 10, max = 100, message = "Description must be between 10 and 100 characters")
    private String description;
}
