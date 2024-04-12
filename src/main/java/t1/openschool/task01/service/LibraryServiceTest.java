package t1.openschool.task01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import t1.openschool.task01.annotation.TrackAsyncTime;
import t1.openschool.task01.annotation.TrackTime;
import t1.openschool.task01.model.Book;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibraryServiceTest{

    private final Book book;

    @TrackTime
    public void setBookName() {
        book.setName("The Adventures of Tom Sawyer");
        log.info("Set book's name: {}", book.getName());
    }

    @TrackAsyncTime
    public void addBookToLibrary() {
        ArrayList<Book> bookList = new ArrayList<>();
        for (int i = 0; i <= 100000; i++) {
            bookList.add(book);
        }
        log.info("New book added to the library: {}", book.getName());
    }
}
