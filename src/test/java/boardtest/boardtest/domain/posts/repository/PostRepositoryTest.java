package boardtest.boardtest.domain.posts.repository;

import boardtest.boardtest.domain.posts.entity.Posts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostsRepository postRepository;

    @Test
    public void 게시글저장하여_불러오기() throws Exception{
        //given
        String title = "test";
        String content = "test1";

        postRepository.save(Posts.builder().title(title).content(content).author("tttt@naver.com").build());

        //when
        List<Posts> postsList = postRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}