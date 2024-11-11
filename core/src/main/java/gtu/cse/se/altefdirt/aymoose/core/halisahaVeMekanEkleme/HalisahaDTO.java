package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import java.util.List;

public record HalisahaDTO(
    Long id,

    Long mekanId,

    String olculer,

    String kapasite, 

    List<Saat> saatAraliklari 
) {}

