package boardtest.boardtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/sample/")
public class SampleController {

    @GetMapping("/all")
    public void exAll(){
        log.info("exAll");
    }

    @GetMapping("/user")
    public void exUser(){
        log.info("exUser");
    }

    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin");
    }
}
