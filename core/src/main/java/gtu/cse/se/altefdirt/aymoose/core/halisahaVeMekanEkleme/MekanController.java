package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping("/mekanlar") 
public class MekanController {
    private final MekanService mekanService;

    public MekanController(MekanService mekanService) {
        this.mekanService = mekanService;
    }

    @GetMapping 
    public List<MekanDTO> getAllMekanlar() {
        return mekanService.getAllMekanlar();
    }

    @GetMapping("/{id}") 
    public Optional<MekanDTO> getMekanById(@PathVariable Long id) {
        return mekanService.getMekanById(id);
    }

    @PostMapping 
    public MekanDTO createMekan(@RequestBody MekanDTO mekanDTO) {
        return mekanService.createMekan(mekanDTO);
    }

    @PutMapping("/{id}") 
    public MekanDTO updateMekan(@PathVariable Long id, @RequestBody MekanDTO mekanDTO) {
        return mekanService.updateMekan(id, mekanDTO);
    }

    @DeleteMapping("/{id}") 
    public void deleteMekan(@PathVariable Long id) {
        mekanService.deleteMekan(id);
    }
}
