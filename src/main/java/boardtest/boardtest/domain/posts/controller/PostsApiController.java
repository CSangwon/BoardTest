package boardtest.boardtest.domain.posts.controller;

import boardtest.boardtest.domain.posts.dto.MovieResponseDto;
import boardtest.boardtest.domain.posts.dto.PostsResponseDto;
import boardtest.boardtest.domain.posts.dto.PostsSaveRequestDto;
import boardtest.boardtest.domain.posts.dto.PostsUpdateRequestDto;
import boardtest.boardtest.domain.posts.service.MovieService;
import boardtest.boardtest.domain.posts.service.PostsService;
import boardtest.boardtest.global.config.auth.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final MovieService movieService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postsService.save(postsSaveRequestDto, userDetails.getMember());
    }

    @PostMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
        return postsService.update(id, postsUpdateRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/movies/{keyword}")
    public MovieResponseDto findmovie(@PathVariable String keyword) {
        return movieService.findByKeyword(keyword);
    }
}
