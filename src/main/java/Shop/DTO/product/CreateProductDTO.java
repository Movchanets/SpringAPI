package Shop.DTO.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateProductDTO {
    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Length(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;
    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be greater than 0")
    private double price;
    @NotNull(message = "Category is mandatory")
    private int categoryId;
    @NotNull(message = "Image is mandatory")
    private List<MultipartFile> files;
}
