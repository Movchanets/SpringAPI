package Shop.DTO.product;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateProductDTO {

    @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Length(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @Min(value = 0, message = "Price must be greater than 0")
    private double price;
    private int category_id;
    @Nullable
    private List<String> removeFiles = new ArrayList<>();
    @Nullable
    private List<MultipartFile> files = new ArrayList<>();
}
