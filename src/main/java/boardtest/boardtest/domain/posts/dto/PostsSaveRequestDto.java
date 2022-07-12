package boardtest.boardtest.domain.posts.dto;

import boardtest.boardtest.domain.member.entity.Member;
import boardtest.boardtest.domain.posts.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private Member member;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
