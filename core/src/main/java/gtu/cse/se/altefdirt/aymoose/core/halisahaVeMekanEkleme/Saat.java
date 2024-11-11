package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class Saat {
    private LocalTime baslangicSaati;
    private LocalTime bitisSaati;

    public Saat() {}

    public Saat(LocalTime baslangicSaati, LocalTime bitisSaati) {
        this.baslangicSaati = baslangicSaati;
        this.bitisSaati = bitisSaati;
    }

    public LocalTime getBaslangicSaati() {
        return baslangicSaati;
    }

    public void setBaslangicSaati(LocalTime baslangicSaati) {
        this.baslangicSaati = baslangicSaati;
    }

    public LocalTime getBitisSaati() {
        return bitisSaati;
    }

    public void setBitisSaati(LocalTime bitisSaati) {
        this.bitisSaati = bitisSaati;
    }
}

