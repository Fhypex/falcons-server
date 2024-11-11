package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageFactory;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa.ImageEntity;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa.JpaImageRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class ImageRepositryImpl implements ImageRepository {

    private final ImageFactory imageFactory;
    private final JpaImageRepository jpaImageRepository;
    
    private Image build(ImageEntity imageEntity) {
        return imageFactory.load(AggregateId.from(imageEntity.getId()), 
                                 AggregateId.from(imageEntity.getRelationId()),
                                 imageEntity.getImageId(),
                                 imageEntity.getExtension()                                                                 
                                );
    }

    @Override
    public Image save(Image image) {
        ImageEntity imageEntity = jpaImageRepository.save(ImageEntity.from(image));
        return build(imageEntity);
    }


    @Override
    public Optional<Image> findById(AggregateId id) {
        ImageEntity imageEntity = jpaImageRepository.findById(id.value()).get();
        
        return Optional.of(build(imageEntity));
    }

    @Override
    public List<Image> findAll() {
        return jpaImageRepository.findAll().stream().map(entity -> {
            
            return build(entity);
        }).collect(Collectors.toList());
    }
}