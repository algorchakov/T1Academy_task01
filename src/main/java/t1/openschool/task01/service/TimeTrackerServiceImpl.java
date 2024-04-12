package t1.openschool.task01.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import t1.openschool.task01.dto.TimeTrackerResponseDTO;
import t1.openschool.task01.model.TimeTracker;
import t1.openschool.task01.repository.TimeTrackerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TimeTrackerServiceImpl implements TimeTrackerService {

    private final TimeTrackerRepository trackerRepository;

    @Autowired
    public TimeTrackerServiceImpl(TimeTrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
    }
    @Async
    public void save(TimeTracker timeTracker) {
        trackerRepository.save(timeTracker);
    }

    public String getById(Long id) {
        Optional<TimeTracker> timeTracker = trackerRepository.findById(id);
        return timeTracker.map(tracker -> tracker.getMethodName() + ", "
                + tracker.getExecutionTime())
                .orElse("Nothing found\n");
    }

    public List<TimeTrackerResponseDTO> findByName(@PathVariable String methodName) {
        List<TimeTracker> timeTracker = trackerRepository.findByMethodName(methodName);
        return timeTracker.stream()
                .map(this::getAllTimeTrackerResponseDTO)
                .collect(Collectors.toList());
    }

    private TimeTrackerResponseDTO getAllTimeTrackerResponseDTO(TimeTracker timeTracker) {
        return TimeTrackerResponseDTO.builder().id(timeTracker.getId())
                .methodName(timeTracker.getMethodName())
                .executionTime(timeTracker.getExecutionTime())
                .build();
    }
}
