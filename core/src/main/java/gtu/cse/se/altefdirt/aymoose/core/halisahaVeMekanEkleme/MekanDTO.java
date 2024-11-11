package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

public record MekanDTO(
    Long id,
    Long kullaniciId,
    String konum,
    String iletisimBilgileri,
    String mekanAdi,
    String telefonNumarasi,
    String tesisAciklamasi,
    int sahaSayisi
) {}
  

