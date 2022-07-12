package boardtest.boardtest.global.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleIllegalStateException(Exception ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }


    //== 유효성 검사 예외처리 핸들러 ==//
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError: bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append("\n");
        }

        ApiException apiException = new ApiException(
                stringBuilder.toString(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
