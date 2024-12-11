package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.rest.dto;

import java.util.UUID;
import org.apache.commons.lang3.Validate;

public record CreateImageRequestDTO(
        UUID relationId) {
    public CreateImageRequestDTO(UUID relationId) {
        Validate.notNull(relationId, "Relation ID cannot be null");
        this.relationId = relationId;
    }
}