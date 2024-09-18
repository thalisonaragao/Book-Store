package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookRecordDtos;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;

import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public BookModel saveBook(BookRecordDtos bookRecordDtos) {
        BookModel book = new BookModel();
        book.setTitle(bookRecordDtos.title());
        book.setPublisher(publisherRepository.findById(bookRecordDtos.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDtos.authorsIds()).stream().collect(Collectors.toSet()));

        ReviewModel review = new ReviewModel();
        review.setComment(bookRecordDtos.reviewComment());
        review.setBook(book);
        book.setReview(review);

        return bookRepository.save(book);
    }
}
