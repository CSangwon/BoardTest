package boardtest.boardtest.domain.posts.service;

import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.posts.dto.PostsListResponseDto;
import boardtest.boardtest.domain.posts.dto.PostsResponseDto;
import boardtest.boardtest.domain.posts.dto.PostsSaveRequestDto;
import boardtest.boardtest.domain.posts.dto.PostsUpdateRequestDto;

import java.util.List;

public interface PostsService {

    Long save(PostsSaveRequestDto postsSaveRequestDto, Member member);

    Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto);

    List<PostsListResponseDto> findAllDesc();

    PostsResponseDto findById(Long id);
}
