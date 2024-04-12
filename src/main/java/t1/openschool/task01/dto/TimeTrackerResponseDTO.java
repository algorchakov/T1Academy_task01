package t1.openschool.task01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeTrackerResponseDTO {
    Long id;
    String methodName;
    Float executionTime;
}
