package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason="error.bad") // 이 exception의 상태 코드와 reason message 설
public class BadRequestException extends RuntimeException {

}
