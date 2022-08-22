package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();

    }

    public void addNewBook(Book book) {
        Optional<Book> bookOptional = bookRepository
                .findBookByIsbn(book.getIsbn());

        if(bookOptional.isPresent()) {
            throw new IllegalStateException("book taken");
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if(!exists) {
            throw new IllegalStateException("book with id " + bookId + " does not exists");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String title, String author, String isbn) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("student with id " + bookId + " does not exist"));

        if(title != null && title.length() > 0 && !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }

        if(author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)) {
            book.setAuthor(author);
        }

        if(isbn != null && isbn.length() > 0 && !Objects.equals(book.getIsbn(), isbn)) {
            book.setIsbn(isbn);
        }
    }
}
