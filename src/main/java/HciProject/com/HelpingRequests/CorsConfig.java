package HciProject.com.HelpingRequests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class CorsConfig {

//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 registry.addMapping("/**") // لكل المسارات
//                         .allowedOrigins("*") // من أي origin
//                         .allowedMethods("*") // كل الميثودز (GET, POST, PUT, DELETE, ...)
//                         .allowedHeaders("*"); // كل الهيدرز
//             }
//         };
//     }
// }

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allowing requests from your frontend
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("https://y-zer0.github.io")
                // .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH") // Allow these methods
                .allowedHeaders("*")
                .allowCredentials(true); // Allow all headers (you can restrict if needed)
    }
}
