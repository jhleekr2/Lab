package Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//@RestController // controller 어노테이션과 responsebody 어노테이션을 결합한 것
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String test() {
        return "root url call";
    }

    @GetMapping("json")
    public Map<String, String> jsontest() {
        Map<String, String> res = new HashMap<>();
        res.put("key", "value");
        return res;
    }

    @GetMapping("test1")
    public String test1(HttpServletRequest req) {
        String fullUrl = req.getRequestURL().toString();
        System.out.println("Full URL: " + fullUrl);
        // 요청 URI 얻기
        String uri = req.getRequestURI();
        System.out.println("URI: " + uri);

        // 요청 경로 얻기
        String contextPath = req.getContextPath();
        System.out.println("Context Path: " + contextPath);

        return "test1";
    }
}
