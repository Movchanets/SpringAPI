package Shop.storage;

import org.springframework.core.io.Resource;

import java.nio.file.Path;

public interface StorageService {
    void init();
    Resource loadAsResource(String filename);
    String save(String base64);

    Path load(String filename);
    boolean delete(String filename);
}
