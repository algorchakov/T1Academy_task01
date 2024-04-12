package t1.openschool.task01.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Component
@Table(name = "timetracker")
public class TimeTracker {
    @TableGenerator(name = "myGenerator", allocationSize = 1, initialValue = 20)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "myGenerator")
    private Long id;
    @JoinColumn(name = "method_name")
    private String methodName;
    @JoinColumn(name = "execution_time")
    private Float executionTime;
}


