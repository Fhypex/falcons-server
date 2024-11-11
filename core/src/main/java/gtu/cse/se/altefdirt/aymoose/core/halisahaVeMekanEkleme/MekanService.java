package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MekanService {
    private final MekanRepository mekanRepository;
    private final MekanMapper mekanMapper;

    public MekanService(MekanRepository mekanRepository, MekanMapper mekanMapper) {
        this.mekanRepository = mekanRepository;
        this.mekanMapper = mekanMapper;
    }

    public List<MekanDTO> getAllMekanlar() {
        return mekanRepository.findAll()
                .stream()
                .map(mekanMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<MekanDTO> getMekanById(Long id) {
        return mekanRepository.findById(id)
                .map(mekanMapper::toDTO);
    }

    public MekanDTO createMekan(MekanDTO mekanDTO) {
        Mekan mekan = mekanMapper.toEntity(mekanDTO);
        Mekan savedMekan = mekanRepository.save(mekan);
        return mekanMapper.toDTO(savedMekan);
    }

    public MekanDTO updateMekan(Long id, MekanDTO mekanDTO) {
        return mekanRepository.findById(id)
            .map(existingMekan -> {
                existingMekan.setKullaniciId(mekanDTO.kullaniciId());
                existingMekan.setKonum(mekanDTO.konum());
                existingMekan.setIletisimBilgileri(mekanDTO.iletisimBilgileri());
                existingMekan.setMekanAdi(mekanDTO.mekanAdi());
                existingMekan.setTelefonNumarasi(mekanDTO.telefonNumarasi());
                existingMekan.setTesisAciklamasi(mekanDTO.tesisAciklamasi());
                existingMekan.setSahaSayisi(mekanDTO.sahaSayisi());
                Mekan updatedMekan = mekanRepository.save(existingMekan);
                return mekanMapper.toDTO(updatedMekan);
            }).orElseThrow(() -> new RuntimeException("Mekan not found"));
    }

    public void deleteMekan(Long id) {
        mekanRepository.deleteById(id);
    }
}

