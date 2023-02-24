package Shop.Controllers;

import Shop.DTO.UploadImageDto;
import Shop.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@AllArgsConstructor
public class HomeController {
    private final StorageService storageService;
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> servefile(@PathVariable String filename) throws Exception {
        Resource file = storageService.loadAsResource(filename);
        String urlFileName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\""+urlFileName+"\"")
                .body(file);
    }
    @PostMapping("/upload")
    public String upload(@RequestBody UploadImageDto dto) {
        String filename = storageService.save(dto.getBase64());
        return filename;
    }

}
