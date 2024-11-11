package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mekan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long kullaniciId;

    @NotNull
    private String konum;

    @NotNull
    private String iletisimBilgileri;

    @NotNull
    private String mekanAdi;

    @NotNull
    private String telefonNumarasi;

    private String tesisAciklamasi;

    private int sahaSayisi;
}
