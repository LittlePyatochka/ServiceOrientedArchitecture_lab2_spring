package kamysh.service;

import kamysh.controller.FilterConfiguration;
import kamysh.dto.HealthCountDto;
import kamysh.dto.SpaceMarineDto;
import kamysh.dto.SpaceMarineWithIdDto;
import kamysh.entity.SpaceMarine;
import kamysh.exceptions.EntityEntryNotFound;
import kamysh.mapper.SpaceMarineMapper;
import kamysh.repository.*;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpaceMarineService {

    private final SpaceMarineRepository spaceMarineRepository;
    private final SpaceMarineMapper spaceMarineMapper;

    private final CoordinatesRepository coordinatesRepository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public SpaceMarineService(SpaceMarineRepository spaceMarineRepository,
                              CoordinatesRepository coordinatesRepository, ChapterRepository chapterRepository) {
        this.spaceMarineRepository = spaceMarineRepository;
        this.coordinatesRepository = coordinatesRepository;
        this.chapterRepository = chapterRepository;
        this.spaceMarineMapper = new SpaceMarineMapper();
    }

    @SneakyThrows
    public List<SpaceMarineDto> findAll(FilterConfiguration filterConfiguration) {
        return this.spaceMarineRepository
                .findAllByFilterConfiguration(filterConfiguration)
                .stream()
                .map(spaceMarineMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public SpaceMarineDto saveOrUpdate(@Valid SpaceMarineWithIdDto dto) {
        SpaceMarine spaceMarine = spaceMarineMapper.dtoToEntity(dto);
        spaceMarine.setCoordinates(coordinatesRepository.findById(dto.getCoordinates()).orElseThrow(EntityEntryNotFound::new));
        spaceMarine.setChapter(chapterRepository.findById(dto.getChapter()).orElseThrow(EntityEntryNotFound::new));
        if (spaceMarine.getId() == null) spaceMarine.setCreationDate(new Date());
        return spaceMarineMapper.entityToDto(spaceMarineRepository.save(spaceMarine));
    }

    @SneakyThrows
    public SpaceMarineDto findById(Long id) {
        return spaceMarineMapper.entityToDto(spaceMarineRepository.findById(id).orElseThrow(() -> new EntityEntryNotFound(id)));
    }

    @SneakyThrows
    public void delete(Long id) {
        spaceMarineRepository.delete(spaceMarineRepository.findById(id).orElseThrow(() -> new EntityEntryNotFound(id)));
    }

    @SneakyThrows
    public Boolean deleteLoyal(Boolean loyal) {
        Optional<SpaceMarine> spaceMarineOptional = spaceMarineRepository.findAll()
                .stream()
                .filter(spaceMarine -> spaceMarine.isLoyal() == loyal)
                .findFirst();
        if (!spaceMarineOptional.isPresent()) return Boolean.FALSE;
        spaceMarineRepository.delete(spaceMarineOptional.get());
        return Boolean.TRUE;
    }

    @SneakyThrows
    public SpaceMarineDto getByMinHealthCount() {
        return spaceMarineMapper.entityToDto(
                spaceMarineRepository
                        .findAll()
                        .stream()
                        .min(Comparator.comparing(SpaceMarine::getHeartCount))
                        .orElseThrow(EntityEntryNotFound::new));
    }

    @SneakyThrows
    public HealthCountDto getHealthCountLessThen(Integer health) {
        return HealthCountDto
                .builder()
                .healthCount(
                        (int) spaceMarineRepository
                                .findAll()
                                .stream()
                                .filter(spaceMarine -> spaceMarine.getHeartCount() < health)
                                .count())
                .build();
    }
}
