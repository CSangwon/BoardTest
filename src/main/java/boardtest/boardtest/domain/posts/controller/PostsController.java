package boardtest.boardtest.domain.posts.controller;

import boardtest.boardtest.domain.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/movie/moviefind")
    public String movieFind(){
        return "movie/movie-find";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts/posts-save";
    }

}
