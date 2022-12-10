package jp.co.planaria.sample.motocatalog.beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {

  // バイク番号
  private Integer motoNo;
  // バイク名
  private String motoName;
  // シート高
  private Integer seatHeight;
  // シリンダー
  private Integer cylinder;
  // 冷却方式
  private String cooling;
  // 価格
  private Integer price;
  // コメント
  private String comment;
  // ブランドID BrandクラスのIDを呼び出す
  private Brand brand;
  // バージョン
  private Integer version;
  // 登録日時
  private LocalDateTime insDt;
  // 更新日時
  private LocalDateTime updDt;

  // public void setMotoNo(String motoNo){ //motoNoというフィールドに値をセットするメソッド
  // this.motoNo = motoNo;
  // }
  // public String getMotoNo(){ //motoNoから値を取り出すメソッド
  // return this.motoNo;
  // }
}
