package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping //스프링은 @Controller or @RequestMapping 중 하나가 있으면 스프링 컨트롤러로 인식한다.
@ResponseBody
public interface OrderControllerV1 {
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);
    //인터페이스에서는 requestParam 없으면 인식 못하는 경우가 발생할 수도 있다.

    @GetMapping("/v1/no-log")
    String noLog();
}
