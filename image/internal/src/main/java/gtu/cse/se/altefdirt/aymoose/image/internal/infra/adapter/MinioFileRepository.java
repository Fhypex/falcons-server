package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Bucket;

@Component
public class MinioFileRepository  {

    private final MinioClient minioClient;
    private final String defaultBucketName;

    public MinioFileRepository(MinioClient minioClient, @Value("${minio.bucket.name}") String defaultBucketName) {
        this.minioClient = minioClient;
        this.defaultBucketName = defaultBucketName;
    }

    public List<Bucket> getAllBuckets() throws Exception {
        return minioClient.listBuckets();
    }

    public String createBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
            return bucketName + " bucked created.";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public byte[] getFile(String objectName, String bucketName) {
        try {
            InputStream file = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName).build());
            return file.readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String uploadFile(MultipartFile file, String bucketName, String objectName) throws IOException {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(),file.getSize(),-1).build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        }

        try {
            return objectName;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteFile(String objectName, String bucketName) throws IOException {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("The file cannot be delete on the internal storage. Please retry later", e);
        }
    }
}