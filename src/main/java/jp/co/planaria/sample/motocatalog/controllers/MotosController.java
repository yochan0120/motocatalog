package jp.co.planaria.sample.motocatalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.planaria.sample.motocatalog.beans.Brand;
import jp.co.planaria.sample.motocatalog.beans.Motorcycle;
import jp.co.planaria.sample.motocatalog.beans.SearchForm;
import jp.co.planaria.sample.motocatalog.services.MotosService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j  //ログ部品を使えるようになる
public class MotosController {

  @Autowired
  MotosService service;

  @RequestMapping("/hello")
    public String hello(@RequestParam String name, Model model){
      model.addAttribute("name", name);
      return "test";
    }

    @GetMapping("/motos")
    public String motos(Model model){
      //ブランド
      List<Brand> brands = new ArrayList<>();
      brands = service.getBrands();

      //バイク
      List<Motorcycle> motos = new ArrayList<>();
      SearchForm condition = new SearchForm();
      motos = service.getMotos(condition);
    
      model.addAttribute("brands", brands);  //インスタンスと同じ名前のキー名でmodelに情報をセットしている
      model.addAttribute("motos", motos);

      log.debug("motos: {}", motos);  //ログ出力する

      return "moto_list";
    }
  }
