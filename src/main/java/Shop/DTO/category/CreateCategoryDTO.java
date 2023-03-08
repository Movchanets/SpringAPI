package Shop.DTO.category;



import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateCategoryDTO {
    @NotBlank(message = "Name is mandatory")
   @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotNull(message = "Image is mandatory")
    private MultipartFile file;
    @Length(min = 10, max = 100, message = "Description must be between 10 and 100 characters")
    private String description;
}