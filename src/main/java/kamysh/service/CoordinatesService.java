package kamysh.service;

import kamysh.dto.CoordinatesDto;
import kamysh.exceptions.EntityEntryNotFound;
import kamysh.mapper.CoordinatesMapper;
import kamysh.repository.CoordinatesRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;
    private final CoordinatesMapper coordinatesMapper;

    @Autowired
    public CoordinatesService(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
        this.coordinatesMapper = new CoordinatesMapper();
    }


    public List<CoordinatesDto> findAll() {
        return this.coordinatesRepository.findAll().stream().map(coordinatesMapper::entityToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public CoordinatesDto save(@Valid CoordinatesDto dto) {
        return coordinatesMapper.entityToDto(coordinatesRepository.save(coordinatesMapper.dtoToEntity(dto)));
    }

    @SneakyThrows
    public CoordinatesDto findById(Long id) {
        return coordinatesMapper.entityToDto(coordinatesRepository.findById(id).orElseThrow(() -> new EntityEntryNotFound(id)));
    }

    @SneakyThrows
    public void delete(Long id) {
        coordinatesRepository.delete(coordinatesRepository.findById(id).orElseThrow(() -> new EntityEntryNotFound(id)));
    }
}
