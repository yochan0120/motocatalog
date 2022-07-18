package jp.co.planaria.sample.motocatalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MotosController {

  @RequestMapping("/hello")
    public String hello(@RequestParam String name, Model model){
      model.addAttribute("name", name);
      return "test";
    }
  }
