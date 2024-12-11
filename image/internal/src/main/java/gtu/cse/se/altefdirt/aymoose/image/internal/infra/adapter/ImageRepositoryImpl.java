package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.BaseImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageFactory;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa.ImageEntity;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa.JpaImageRepository;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.mapper.ImageMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;

@Component
@Transactional
class ImageRepositryImpl implements ImageRepository {

    private final String publicUri;
    private final ImageFactory factory;
    private final ImageMapper mapper;
    private final JpaImageRepository jpaRepository;
    private final MinioFileRepository minioFileRepository;

    public ImageRepositryImpl(@Value("${image.publicUri}") String publicUri, ImageFactory imageFactory,
            JpaImageRepository jpaImageRepository, MinioFileRepository minioFileRepository, ImageMapper imageMapper) {
        this.publicUri = publicUri;
        this.factory = imageFactory;
        this.jpaRepository = jpaImageRepository;
        this.minioFileRepository = minioFileRepository;
        this.mapper = imageMapper;
    }

    @Override
    public Image save(BaseImage image, MultipartFile file) {
        String url = null;
        try {
            url = minioFileRepository.uploadFile(file, image.id().toString());
        } catch (Exception e) {
            throw new RuntimeException("An error occured during saving to cloud", e);
        }
        if (url == null) {
            throw new RuntimeException("An error occured during saving to cloud");
        }
        return mapper.toDomain(jpaRepository.save(mapper.toEntityFromBase(image, publicUri + url)));
    }

    @Override
    public Integer deleteById(AggregateId id) {
        ImageEntity imageEntity = jpaRepository.findById(id.value()).get();
        try {
            minioFileRepository.deleteFile(imageEntity.getId().toString());
        } catch (IOException e) {
            throw new RuntimeException("An error occured during deleting from cloud", e);
        }
        jpaRepository.deleteById(id.value());
        return 1;
    }

    @Override
    public Optional<Image> findById(AggregateId id) {
        return Optional.of(mapper.toDomain(jpaRepository.findById(id.value()).get()));
    }

    @Override
    public List<Image> findByRelationId(AggregateId relationId) {
        return jpaRepository.findAllByRelationId(relationId.value()).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Image save(AggregateId relationId, MultipartFile file) {
        BaseImage baseImage = factory.createBase(relationId, file);
        String url = null;
        try {
            url = minioFileRepository.uploadFile(file, baseImage.id().asString());
        } catch (Exception e) {
            throw new RuntimeException("An error occured during saving to cloud", e);
        }
        if (url == null) {
            throw new RuntimeException("An error occured during saving to cloud");
        }
        return mapper.toDomain(jpaRepository.save(mapper.toEntityFromBase(baseImage, publicUri + url)));
    }

    @Override
    public Integer deleteByRelationId(AggregateId relationId) {
        List<ImageEntity> imageEntities = jpaRepository.findAllByRelationId(relationId.value());
        try {
            minioFileRepository
                    .deleteFiles(imageEntities.stream().map(ImageEntity::getId).map(UUID::toString).toList());
        } catch (Exception e) {
            throw new RuntimeException("An error occured during deleting from cloud", e);
        }
        jpaRepository.deleteByRelationId(relationId.value());
        return 1;
    }

}