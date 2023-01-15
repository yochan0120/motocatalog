package jp.co.planaria.sample.motocatalog.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jp.co.planaria.sample.motocatalog.beans.Brand;
import jp.co.planaria.sample.motocatalog.beans.Motorcycle;
import jp.co.planaria.sample.motocatalog.beans.SearchForm;
import jp.co.planaria.sample.motocatalog.forms.MotoForm;
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

    /**
     * バイク一覧を検索する
     * @param searchForm 検索条件
     * @param model Mpdel
     * @return 遷移先
     */
    @GetMapping("/motos")
    public String motos(@Validated SearchForm searchForm, BindingResult result, Model model){
      log.info("検索条件: {}", searchForm);
      if(result.hasErrors()) {
        // 入力チェックでエラーがある場合
        return "moto_list";
      }
      // ブランドリストを準備
      this.setBrands(model);

      // バイク
      List<Motorcycle> motos = new ArrayList<>();
      motos = service.getMotos(searchForm);
    
      model.addAttribute("motos", motos);
      model.addAttribute("datetime", LocalDateTime.now());

      log.debug("motos: {}", motos);  //ログ出力する

      return "moto_list";
    }
    /**
     * 検索条件をクリアする
     * @param searchForm 検索条件
     * @param model Model
     * @return 遷移先
     */
    @GetMapping("/motos/reset")
    public String reset(SearchForm searchForm, Model model){
      // ブランドリストを準備
      this.setBrands(model);

      // 検索条件のクリア
      searchForm = new SearchForm();
      return "moto_list";
    }
    /**
     * 更新画面の初期表示
     * @param motoNo バイク番号
     * @param motoForm 入力内容
     * @param model Model
     * @return 遷移先
     */
    @GetMapping("/motos/{motoNo}")
    public String initUpdate(@PathVariable("motoNo") Integer motoNo, @ModelAttribute MotoForm motoForm, Model model) {
      // ブランドリストを準備
      this.setBrands(model);

      // バイク番号を条件にバイク情報を取得
      Motorcycle moto = service.getMotos(motoNo);
      // 検索結果を入力内容に詰め替える
      BeanUtils.copyProperties(moto, motoForm);
      return "moto";
    }

    @PostMapping("/motos/save")
    public String save(@ModelAttribute MotoForm motoForm, BindingResult result){
      try {
        log.info("motoForm: {}", motoForm);
        Motorcycle moto = new Motorcycle();
        // 検索結果を入力内容に詰め替える
        BeanUtils.copyProperties(motoForm, moto);
        // 情報を更新する
        int cnt = service.save(moto);
        log.info("{}件更新", cnt);
  
        // リダイレクト（一覧に遷移）
        return "redirect:/motos";

      } catch (OptimisticLockingFailureException e) {
        result.addError(new ObjectError("global", e.getMessage()));
        return "moto";

      }

    }

    /**
     * ブランドリストをModelにセットする
     * @param model Model
     */
    private void setBrands(Model model){
      // ブランド
      List<Brand> brands = new ArrayList<>();
      brands = service.getBrands();
      model.addAttribute("brands", brands);
    }
  }