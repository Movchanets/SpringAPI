package Shop.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    void init();
    Resource loadAsResource(String filename);
    String save(String base64);
    String saveMultipartFile(MultipartFile file);

    Path load(String filename);
    boolean delete(String filename);


}
