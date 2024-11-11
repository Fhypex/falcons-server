package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HalisahaService {
    private final HalisahaRepository halisahaRepository;
    private final HalisahaMapper halisahaMapper;

    public HalisahaService(HalisahaRepository halisahaRepository, HalisahaMapper halisahaMapper) {
        this.halisahaRepository = halisahaRepository;
        this.halisahaMapper = halisahaMapper;
    }

    public List<HalisahaDTO> getAllHalisahalar() {
        return halisahaRepository.findAll()
                .stream()
                .map(halisahaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<HalisahaDTO> getHalisahaById(Long id) {
        return halisahaRepository.findById(id)
                .map(halisahaMapper::toDTO);
    }

    public List<HalisahaDTO> getHalisahalarByMekanId(Long mekanId) {
        return halisahaRepository.findByMekanId(mekanId)
                .stream()
                .map(halisahaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public HalisahaDTO createHalisaha(HalisahaDTO halisahaDTO) {
        Halisaha halisaha = halisahaMapper.toEntity(halisahaDTO);
        Halisaha savedHalisaha = halisahaRepository.save(halisaha);
        return halisahaMapper.toDTO(savedHalisaha);
    }

    public HalisahaDTO updateHalisaha(Long id, HalisahaDTO halisahaDTO) {
        return halisahaRepository.findById(id)
            .map(existingHalisaha -> {
                existingHalisaha.setMekanId(halisahaDTO.mekanId());
                existingHalisaha.setOlculer(halisahaDTO.olculer());
                existingHalisaha.setKapasite(halisahaDTO.kapasite());
                existingHalisaha.setSaatAraliklari(halisahaDTO.saatAraliklari());
                Halisaha updatedHalisaha = halisahaRepository.save(existingHalisaha);
                return halisahaMapper.toDTO(updatedHalisaha);
            }).orElseThrow(() -> new RuntimeException("Halisaha not found"));
    }

    public void deleteHalisaha(Long id) {
        halisahaRepository.deleteById(id);
    }
}
