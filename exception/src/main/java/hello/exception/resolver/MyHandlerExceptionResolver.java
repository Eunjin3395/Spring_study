package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) { // 예외는 그냥 먹어버림
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage()); // 내가 원하는 값으로 에러 설정해서 보냄
                return new ModelAndView(); // ModelAndView를 빈 값으로 리턴하면 서블릿 -> WAS까지 정상적 흐름으로 리턴된다(위에서 만든 sendError를 가지고.)

            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
    }
}
