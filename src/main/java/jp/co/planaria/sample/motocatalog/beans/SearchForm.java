package jp.co.planaria.sample.motocatalog.beans;

import lombok.Data;

//検索条件
@Data
public class SearchForm {
  //ブランドID
  private String brandId;
  //キーワード
  private String keyword;
}
