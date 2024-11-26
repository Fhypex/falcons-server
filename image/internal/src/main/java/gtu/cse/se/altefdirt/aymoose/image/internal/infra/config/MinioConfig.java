package gtu.cse.se.altefdirt.aymoose.image.internal.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.minio.MinioClient;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MinioConfig {

    private final String accessKey;
    private final String secretKey;
    private final String minioUrl;

    public MinioConfig(@Value("${minio.access.name}") String accessKey, @Value("${minio.access.secret}") String secretKey, @Value("${minio.url}") String minioUrl) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.minioUrl = minioUrl;
    }

    @Bean
    @Primary
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .credentials(accessKey, secretKey)
                .endpoint(minioUrl)
                .build();
    }
}