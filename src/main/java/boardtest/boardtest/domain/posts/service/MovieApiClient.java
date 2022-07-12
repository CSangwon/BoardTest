package boardtest.boardtest.domain.posts.service;

import boardtest.boardtest.domain.posts.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class MovieApiClient {
    private final RestTemplate restTemplate;

    private final String CLIENT_ID = "b1DHtr8oHH187pSACYpu";
    private final String CLIENT_SECRET = "CU7GL9n7db";

    private final String OpenNaverMovieUrl_getMovies = "https://openapi.naver.com/v1/search/movie.json?query={keyword}";

    public MovieResponseDto requestMovie(String keyword) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(OpenNaverMovieUrl_getMovies, HttpMethod.GET, entity, MovieResponseDto.class, keyword).getBody();
    }
}
