package hello;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import usecase.HelloUseCase;
import usecase.SpringRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
public class HelloController {

    @RequestMapping("/{name}")
    public String index(
        @RequestHeader HttpHeaders httpHeaders,
        @PathVariable Map<String, String> pathVariables,
        @RequestParam Map<String, String> requestParams,
        HttpServletRequest servletRequest
    ) {
        return new HelloUseCase().run(new SpringRequest(
            pathVariables,
            requestParams,
            servletRequest.getMethod(),
            httpHeaders.toSingleValueMap()
        ));
    }

}