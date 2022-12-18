package jp.co.planaria.sample.motocatalog.forms;

import jp.co.planaria.sample.motocatalog.beans.Brand;
import lombok.Data;

/*
 * 更新画面の入力内容
 */
@Data
public class MotoForm {
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
}
