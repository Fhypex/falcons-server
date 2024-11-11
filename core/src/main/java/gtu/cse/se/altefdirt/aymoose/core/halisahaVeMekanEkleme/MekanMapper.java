package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import org.springframework.stereotype.Component;

@Component
public class MekanMapper {
    public MekanDTO toDTO(Mekan mekan) {
        return new MekanDTO(
            mekan.getId(),
            mekan.getKullaniciId(),
            mekan.getKonum(),
            mekan.getIletisimBilgileri(),
            mekan.getMekanAdi(),
            mekan.getTelefonNumarasi(),
            mekan.getTesisAciklamasi(),
            mekan.getSahaSayisi()
        );
    }

    public Mekan toEntity(MekanDTO dto) {
        Mekan mekan = new Mekan();
        mekan.setId(dto.id());
        mekan.setKullaniciId(dto.kullaniciId());
        mekan.setKonum(dto.konum());
        mekan.setIletisimBilgileri(dto.iletisimBilgileri());
        mekan.setMekanAdi(dto.mekanAdi());
        mekan.setTelefonNumarasi(dto.telefonNumarasi());
        mekan.setTesisAciklamasi(dto.tesisAciklamasi());
        mekan.setSahaSayisi(dto.sahaSayisi());
        return mekan;
    }
}

