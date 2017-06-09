package errorSimulator;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class Application  {
    
    public static void main(String[] args) throws IOException{
        SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        File file = new ClassPathResource("result.csv").getFile();
        TopErrorSimulator.loadTopErrors(file);
    }

}
