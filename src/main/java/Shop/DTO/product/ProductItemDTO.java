
package Shop.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemDTO {
    private int id;
    private String name;
    private double price;
    private List<String> files = new ArrayList<>();
    private String description;
    private String category;
     private int category_id;

}
