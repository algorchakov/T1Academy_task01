package t1.openschool.task01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import t1.openschool.task01.service.LibraryServiceTest;

@SpringBootApplication
public class Task01Application {
    @Autowired
    LibraryServiceTest library;

    public static void main(String[] args) {
        SpringApplication.run(Task01Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void testMethod() {
        library.setBookName();
        library.addBookToLibrary();
    }

}
