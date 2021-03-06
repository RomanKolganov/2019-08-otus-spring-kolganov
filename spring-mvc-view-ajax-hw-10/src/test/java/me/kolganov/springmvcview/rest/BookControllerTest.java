package me.kolganov.springmvcview.rest;

import me.kolganov.springmvcview.domain.Author;
import me.kolganov.springmvcview.domain.Book;
import me.kolganov.springmvcview.domain.Genre;
import me.kolganov.springmvcview.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Rest контроллер для работы с книгами ")
@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("должен возвращать список книг")
    void getBooksListTest() throws Exception {
        Book book = new Book(1, "testBook");
        book.setAuthor(new Author(1, "testAuthor"));
        book.setGenre(new Genre(1, "testGenre"));

        List<Book> books = Collections.singletonList(book);
        given(bookService.getAll()).willReturn(books);

        this.mockMvc.perform(get("/book/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'testBook', 'authorDto':{'id':1, 'name':'testAuthor'}, 'genreDto':{'id':1, 'name':'testGenre'}}]"));
    }
}
