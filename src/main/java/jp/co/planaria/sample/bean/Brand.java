package jp.co.planaria.sample.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Brand {

  //ブランドID
  private String brandId;
  //ブランド名
  private String brandName;
}
