package kamysh.controller;

import kamysh.dto.CoordinatesDto;
import kamysh.dto.ResultListDto;
import kamysh.service.CoordinatesService;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coordinates")
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    @Autowired
    public CoordinatesController(CoordinatesService coordinatesService) {
        this.coordinatesService = coordinatesService;
    }

    @SneakyThrows
    @GetMapping
    public ResultListDto get() {
        return ResultListDto.builder().results(coordinatesService.findAll()).build();
    }

    @GetMapping("/{id}")
    public CoordinatesDto getById(@PathVariable Long id) {
        return coordinatesService.findById(id);
    }

    @PostMapping
    public CoordinatesDto create(@RequestBody CoordinatesDto dto) {
        return coordinatesService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        coordinatesService.delete(id);
    }
}
