package boardtest.boardtest.domain.posts.service;

import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.posts.dto.PostsListResponseDto;
import boardtest.boardtest.domain.posts.dto.PostsResponseDto;
import boardtest.boardtest.domain.posts.dto.PostsSaveRequestDto;
import boardtest.boardtest.domain.posts.dto.PostsUpdateRequestDto;
import boardtest.boardtest.domain.posts.entity.Posts;
import boardtest.boardtest.domain.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PostsServiceImpl implements PostsService{

    private final PostsRepository postsRepository;

    @Override
    public Long save(PostsSaveRequestDto postsSaveRequestDto, Member member) {
        postsSaveRequestDto.setMember(member);
        return postsRepository.save(postsSaveRequestDto.toEntity()).getId();
    }

    @Override
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostsResponseDto(posts);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
