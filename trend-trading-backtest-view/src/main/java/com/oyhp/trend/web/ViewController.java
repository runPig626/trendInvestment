package com.oyhp.trend.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author OYHP
 */
@Controller
public class ViewController {
    @Value("${version}")
    String version;

    @GetMapping("/")
    public String view(Model model) throws Exception {
        model.addAttribute("version", version);
        return "view";
    }

}