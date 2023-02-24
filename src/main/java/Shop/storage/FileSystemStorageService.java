package Shop.storage;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;
    public FileSystemStorageService(StorageProperties properties) {
        rootLocation = Paths.get(properties.getLocation());
    }
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    @Override
    public String save(String base64) {
        try {
            if(base64.isEmpty()) {
                throw new StorageException("Пустий base64");
            }
            UUID uuid = UUID.randomUUID();
            String randomFileName = uuid.toString()+".jpg";
            String [] charArray = base64.split(",");
            Base64.Decoder decoder = Base64.getDecoder();
            byte [] bytes = new byte[0];
            bytes = decoder.decode(charArray[1]);
            String folder = rootLocation.toString()+"/"+randomFileName;
            new FileOutputStream(folder).write(bytes);
            return randomFileName;
        } catch (IOException e) {
            throw new StorageException("Проблема перетворення та збереження base64", e);
        }
    }
    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
    @Override
    public boolean delete(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            System.out.println(file);
            Files.delete(file);
            return true;
        } catch (IOException e) {
            throw new StorageException("Could not delete file: " + filename, e);
        }
    }
}
