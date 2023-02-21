package jp.co.planaria.sample.motocatalog.beans;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

  //ブランドID
  @NotBlank
  private String brandId;
  //ブランド名
  private String brandName;
}
