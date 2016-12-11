package net.dqsy.papermg.sysmanager.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/top")
    public String top(){
        return "top";

    } @RequestMapping("/left")
    public String left(){
        return "left";

    }

    @RequestMapping("/right")
    public String right() {
        return "right";

    }

    @RequestMapping("/index")
    public String index() {
        return "index";

    }


}
