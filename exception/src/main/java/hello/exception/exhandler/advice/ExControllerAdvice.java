package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice() // ControllerAdvice + ResponseBody
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class) // 이 컨트롤러에서 IllegalArgumentException이 발생하면 이 handler가 잡는다.
    // 그리고 아래 로직이 호출된다. 그런데 얘가 RestController이므로 ErrorResult가 JSON으로 변환되어 리턴된다
    // 이 때, JSON으로 변환되며 완전히 정상 흐름으로 리턴되므로 http 상태 코드가 200이 된다.
    // 이걸 의도적으로 400 등으로 바꿔서 응답 내리기 위해 @ResponseStatus를 사용한다.
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    // @ExceptionHandler(UserException.class)
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) { // 여기에 UserException 적어주면 어노테이션에 UserException.class 생략 가능
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER_EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) { // Exception: 최상위 예외. 이 예외를 상속하는 자식 예외들도 잡아준다
        // 위 illegalExHandler, userExHandler에서 잡지 못한 예외들을 여기서 잡아주게 된다
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
