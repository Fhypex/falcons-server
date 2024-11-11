package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Halisaha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long mekanId;

    @NotNull
    private String olculer;

    @NotNull
    private String kapasite; 

    @ElementCollection
    private List<Saat> saatAraliklari; 
}
