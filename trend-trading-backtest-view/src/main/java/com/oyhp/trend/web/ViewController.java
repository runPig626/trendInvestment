package com.oyhp.trend.web;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
  
/**
 * @author OYHP
 */
@Controller
public class ViewController {
    @GetMapping("/")
    public String view() throws Exception {
        return "view";
    }

}