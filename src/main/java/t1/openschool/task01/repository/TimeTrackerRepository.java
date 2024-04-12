package t1.openschool.task01.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t1.openschool.task01.model.TimeTracker;

import java.util.List;

@Repository
public interface TimeTrackerRepository extends CrudRepository<TimeTracker, Long> {
    List<TimeTracker> findByMethodName(String methodName);

    @Query("SELECT SUM(t.executionTime) FROM TimeTracker t WHERE t.methodName = :methodName")
    Long getTotalExecuteTime(@Param("methodName") String methodName);

    @Query("SELECT AVG(t.executionTime) FROM TimeTracker t WHERE t.methodName = :methodName")
    Long getAvgExecuteTime(@Param("methodName") String methodName);
}
