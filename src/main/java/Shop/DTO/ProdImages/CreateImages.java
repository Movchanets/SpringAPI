package Shop.DTO.ProdImages;


import Shop.DTO.product.ProductImageDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateImages {
    @NotNull
    List<ProductImageDTO> images;
}
