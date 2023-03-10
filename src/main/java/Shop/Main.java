package Shop;

import Shop.storage.StorageProperties;
import Shop.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);

    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            try {
                storageService.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}