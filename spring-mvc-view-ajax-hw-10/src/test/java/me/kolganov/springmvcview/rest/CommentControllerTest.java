package me.kolganov.springmvcview.rest;

import me.kolganov.springmvcview.domain.Book;
import me.kolganov.springmvcview.domain.Comment;
import me.kolganov.springmvcview.service.CommentService;
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

@DisplayName("Rest контроллер для работы с комментариями ")
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("должен возвращать список комментариев для книги")
    void getCommentsListTest() throws Exception {
        Comment comment = new Comment(1, "testText");
        comment.setBook(new Book(1, "testBook"));

        List<Comment> comments = Collections.singletonList(comment);
        given(commentService.getAllByBookId(1)).willReturn(comments);

        this.mockMvc.perform(get("/comment/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'text':'testText', 'bookDto':{'id':1, 'name':'testBook'}}]"));
    }
}
