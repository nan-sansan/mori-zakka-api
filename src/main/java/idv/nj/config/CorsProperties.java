package idv.nj.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "security.cors")
public class CorsProperties {
    private List<String> allowedOrigins;
    private List<String> allowedMethods = List.of("*");
    private List<String> allowedHeaders = List.of("*");
    private List<String> exposedHeaders = List.of();
    private Boolean allowCredentials = true;
    private long maxAge = 3600L;

    public List<String> getAllowedOrigins() {
        if (allowedOrigins == null || allowedOrigins.isEmpty()) {
            throw new IllegalArgumentException("security.cors.allowed-origins is required");
        }
        return allowedOrigins;
    }
}
