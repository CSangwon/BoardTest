package boardtest.boardtest.domain.posts.service;

import boardtest.boardtest.domain.posts.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieApiClient movieApiClient;

    @Transactional(readOnly = true)
    public MovieResponseDto findByKeyword(String keyword) {
        return movieApiClient.requestMovie(keyword);
    }
}
