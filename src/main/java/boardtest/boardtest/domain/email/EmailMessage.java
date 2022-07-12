package boardtest.boardtest.domain.email;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailMessage {

    private String to;
    private String title;
    private String message;
}
