package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageFactory;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa.ImageEntity;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa.JpaImageRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
class ImageRepositryImpl implements ImageRepository {

    private final String publicUri;
    private final ImageFactory imageFactory;
    private final JpaImageRepository jpaImageRepository;
    private final MinioFileRepository minioFileRepository;

    public ImageRepositryImpl(@Value("${image.publicUri}") String publicUri, ImageFactory imageFactory, JpaImageRepository jpaImageRepository, MinioFileRepository minioFileRepository) {
        this.publicUri = publicUri;
        this.imageFactory = imageFactory;
        this.jpaImageRepository = jpaImageRepository;
        this.minioFileRepository = minioFileRepository;
    }
    
    private Image build(ImageEntity imageEntity) {
        return imageFactory.load(AggregateId.from(imageEntity.getId()), 
                                 AggregateId.from(imageEntity.getRelationId()),
                                 imageEntity.getName(),
                                 imageEntity.getSize(),
                                 imageEntity.getExtension(),
                                 imageEntity.getUrl()                                                             
                                );
    }

    @Override
    public Image save(Image image, MultipartFile file) {
        String url = null;
        try {
             url = minioFileRepository.uploadFile(file, "server", image.id().value());
        }
        catch (Exception e) {
            throw new RuntimeException("An error occured during saving to cloud", e);
        }
        if(url == null) {
            throw new RuntimeException("An error occured during saving to cloud");
        }
        ImageEntity imageEntity = jpaImageRepository.save(ImageEntity.withUrl(image, publicUri + url));
        return build(imageEntity);
    }


    @Override
    public Optional<Image> findById(AggregateId id) {
        ImageEntity imageEntity = jpaImageRepository.findById(id.value()).get();
        return Optional.of(build(imageEntity));
    }

    @Override
    public List<Image> findAllByRelationId(AggregateId relationId) {
        return jpaImageRepository.findAllByRelationId(relationId.value()).stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String url(Image image) {
        return "";
    }
}