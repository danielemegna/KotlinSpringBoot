package hello;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usecase.SubmitUseCase;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SubmitController {

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ResponseEntity handle(
        @RequestHeader HttpHeaders httpHeaders,
        @PathVariable Map<String, String> pathVariables,
        @RequestParam Map<String, String> requestParams,
        @RequestBody String requestBody,
        HttpServletRequest servletRequest
    ) {
        System.out.println("requestBody = " + requestBody);
        new SubmitUseCase(requestBody).run();
        return responseJson("{\"result\": \"OK\", \"request\": " + requestBody + "}");
    }

    @RequestMapping(value = "/submit")
    public ResponseEntity badRequest() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    private ResponseEntity responseJson(String body) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(body, responseHeaders, HttpStatus.OK);
    }
}
