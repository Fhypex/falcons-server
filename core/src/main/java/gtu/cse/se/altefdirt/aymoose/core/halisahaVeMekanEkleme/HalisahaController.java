package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/halisahalar")
public class HalisahaController {
    private final HalisahaService halisahaService;

    public HalisahaController(HalisahaService halisahaService) {
        this.halisahaService = halisahaService;
    }

    @GetMapping
    public List<HalisahaDTO> getAllHalisahalar() {
        return halisahaService.getAllHalisahalar();
    }

    @GetMapping("/{id}")
    public Optional<HalisahaDTO> getHalisahaById(@PathVariable Long id) {
        return halisahaService.getHalisahaById(id);
    }

    @GetMapping("/mekan/{mekanId}")
    public List<HalisahaDTO> getHalisahalarByMekanId(@PathVariable Long mekanId) {
        return halisahaService.getHalisahalarByMekanId(mekanId);
    }

    @PostMapping
    public HalisahaDTO createHalisaha(@RequestBody HalisahaDTO halisahaDTO) {
        return halisahaService.createHalisaha(halisahaDTO);
    }

    @PutMapping("/{id}")
    public HalisahaDTO updateHalisaha(@PathVariable Long id, @RequestBody HalisahaDTO halisahaDTO) {
        return halisahaService.updateHalisaha(id, halisahaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteHalisaha(@PathVariable Long id) {
        halisahaService.deleteHalisaha(id);
    }
}
