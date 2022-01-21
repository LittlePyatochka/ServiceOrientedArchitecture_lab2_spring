package kamysh.controller;

import kamysh.dto.*;
import kamysh.service.SpaceMarineService;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/space-marine")
public class SpaceMarineController {

    private final SpaceMarineService spaceMarineService;

    @Autowired
    public SpaceMarineController(SpaceMarineService spaceMarineService) {
        this.spaceMarineService = spaceMarineService;
    }

    @SneakyThrows
    @GetMapping
    public ResultListDto get(@RequestParam(required = false) Integer count,
                             @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) String[] order,
                             @RequestParam(required = false) String[] filter) {
        return ResultListDto
                .builder()
                .results(spaceMarineService.findAll(FilterConfiguration
                        .builder()
                        .count(count)
                        .page(page)
                        .order(Arrays.asList(order))
                        .filter(Arrays.asList(filter))
                        .build()))
                .build();
    }

    @GetMapping("/heartCount/min")
    public SpaceMarineDto getByMinHeartCount() {
        return spaceMarineService.getByMinHealthCount();
    }

    @GetMapping("/health/count")
    public HealthCountDto getHealthCountLessThen(@RequestParam Integer value) {
        return spaceMarineService.getHealthCountLessThen(value);
    }

    @GetMapping("/{id}")
    public SpaceMarineDto getById(@PathVariable Long id) {
        return spaceMarineService.findById(id);
    }

    @PostMapping
    public SpaceMarineDto create(@RequestBody SpaceMarineWithIdDto dto) {
        dto.setId(null);
        return spaceMarineService.saveOrUpdate(dto);
    }

    @PutMapping("/{id}")
    public SpaceMarineDto update(@PathVariable Long id, @RequestBody SpaceMarineWithIdDto dto) {
        dto.setId(id);
        return spaceMarineService.saveOrUpdate(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        spaceMarineService.delete(id);
    }
}
