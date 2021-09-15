package IntegratedProject.int222;

import IntegratedProject.int222.uploadfiles.StorageProperties;
import IntegratedProject.int222.uploadfiles.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class Int222Application {

	public static void main(String[] args) {
		SpringApplication.run(Int222Application.class, args);
	}
//	@Bean
//	CommandLineRunner init(StorageService storageService) {
//		return (args) -> {
//			storageService.deleteAll();
//			storageService.init();
//		};
//	}
}
