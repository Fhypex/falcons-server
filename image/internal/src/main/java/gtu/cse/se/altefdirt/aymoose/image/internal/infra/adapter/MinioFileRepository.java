package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteObject;

@Component
public class MinioFileRepository {

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

    public String uploadFile(MultipartFile file, String objectName) throws IOException {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1).build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        }

        try {
            return objectName;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteFiles(List<String> objectNames) {
        try {
                minioClient.removeObjects(RemoveObjectsArgs.builder()
                        .bucket(defaultBucketName)
                        .objects(objectNames.stream().map(DeleteObject::new).toList())
                        .build());
        } catch (Exception e) {
            throw new IllegalStateException("The file cannot be delete on the internal storage. Please retry later", e);
        }
    }

    public void deleteFile(String objectName) throws IOException {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(objectName)
                    .build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("The file cannot be delete on the internal storage. Please retry later", e);
        }
    }
}