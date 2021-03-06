package me.kolganov.springsecurityformbased.hw11.controller;

import me.kolganov.springsecurityformbased.hw11.domain.Author;
import me.kolganov.springsecurityformbased.hw11.domain.Book;
import me.kolganov.springsecurityformbased.hw11.domain.Genre;
import me.kolganov.springsecurityformbased.hw11.service.AuthorService;
import me.kolganov.springsecurityformbased.hw11.service.BookService;
import me.kolganov.springsecurityformbased.hw11.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping(value = "/books")
    public String getAll(Model model) {
        List<Book> books = bookService.getAll();
        List<Author> authors = authorService.getAll();
        List<Genre> genres = genreService.getAll();

        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);

        return "book";
    }

    @PostMapping(value = "/books/delete")
    public String delete(@RequestParam("id") long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PostMapping(value = "/books/create")
    public String create(@RequestParam("name") String name,
                         @RequestParam("author_id") long authorId,
                         @RequestParam("genre_id") long genreId) {
        bookService.save(new Book(name), authorId, genreId);
        return "redirect:/books";
    }

    @GetMapping(value = "/books/edit")
    public String getOne(@RequestParam("id") long id, Model model) {
        Book book = bookService.getById(id);
        List<Author> authors = authorService.getAll();
        List<Genre> genres = genreService.getAll();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "bookEdit";
    }

    @PostMapping(value = "/books/update")
    public String update(@RequestParam("id") long id,
                         @RequestParam("name") String name,
                         @RequestParam("author_id") long authorId,
                         @RequestParam("genre_id") long genreId) {
        bookService.update(new Book(id, name), authorId, genreId);
        return "redirect:/books";
    }
}
