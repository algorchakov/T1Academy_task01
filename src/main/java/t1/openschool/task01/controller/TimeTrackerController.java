package t1.openschool.task01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import t1.openschool.task01.dto.TimeTrackerResponseDTO;
import t1.openschool.task01.repository.TimeTrackerRepository;
import t1.openschool.task01.service.TimeTrackerServiceImpl;

import java.util.List;

@RestController
@Slf4j
public class TimeTrackerController {
    private final TimeTrackerRepository timeTrackerRepository;
    private final TimeTrackerServiceImpl timeTrackerService;

    private TimeTrackerController(TimeTrackerRepository timeTrackerRepository,
                                  TimeTrackerServiceImpl timeTrackerService) {
        this.timeTrackerRepository = timeTrackerRepository;
        this.timeTrackerService = timeTrackerService;
    }

    @GetMapping(path = "/get/{id}")
    public String getById(@PathVariable Long id) {
        return timeTrackerService.getById(id);
    }

    @GetMapping(path = "/getByName/{methodName}")
    public List<TimeTrackerResponseDTO> getListMethod(@PathVariable String methodName) {
        return timeTrackerService.findByName(methodName);
    }

    @GetMapping(path = "/getTotalTime/{methodName}")
    public Long getTotalTimeByMethodName(@PathVariable String methodName) {
        return timeTrackerRepository.getTotalExecuteTime(methodName);
    }

    @GetMapping(path = "/getAvgTime/{methodName}")
    public Long getAvgExecuteTimeByMethodName(@PathVariable String methodName) {
        return timeTrackerRepository.getAvgExecuteTime(methodName);
    }

}
