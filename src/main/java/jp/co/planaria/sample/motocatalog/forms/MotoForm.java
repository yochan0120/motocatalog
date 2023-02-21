package jp.co.planaria.sample.motocatalog.forms;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jp.co.planaria.sample.motocatalog.beans.Brand;
import lombok.Data;

/*
 * 更新画面の入力内容
 */
@Data
public class MotoForm {

    // バイク番号
    private Integer motoNo;
    // バイク名
    @NotBlank
    @Size(min = 1, max = 50)
    private String motoName;
    // シート高
    @Min(0)
    @Max(1000)
    private Integer seatHeight;
    // シリンダー
    @Min(1)
    @Max(4)
    private Integer cylinder;
    // 冷却方式
    @Size(max = 10)
    private String cooling;
    // 価格
    @Min(100000)
    private Integer price;
    // コメント
    @Size(max = 200)
    private String comment;
    // ブランドID BrandクラスのIDを呼び出す
    @Valid
    private Brand brand;
    // バージョン
    private Integer version;
}
