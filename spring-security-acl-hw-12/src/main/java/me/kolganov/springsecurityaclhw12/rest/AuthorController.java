package me.kolganov.springsecurityaclhw12.rest;

import me.kolganov.springsecurityaclhw12.domain.Author;
import me.kolganov.springsecurityaclhw12.rest.dto.AuthorDto;
import me.kolganov.springsecurityaclhw12.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/author/", produces = "application/json")
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAll().stream().map(AuthorDto::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthor(@PathVariable("id") long id) {
        authorService.delete(id);
    }

    @PostMapping("/author/")
    public void createAuthor(@RequestParam("name") String name) {
        authorService.save(new Author(name));
    }

    @PutMapping(value = "/author/{id}")
    public void updateAuthor(@RequestBody AuthorDto authorDto) {
        authorService.update(AuthorDto.toEntity(authorDto));
    }
}
