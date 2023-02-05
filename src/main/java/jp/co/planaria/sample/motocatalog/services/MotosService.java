package jp.co.planaria.sample.motocatalog.services;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.planaria.sample.motocatalog.beans.Brand;
import jp.co.planaria.sample.motocatalog.beans.Motorcycle;
import jp.co.planaria.sample.motocatalog.beans.SearchForm;
import jp.co.planaria.sample.motocatalog.mappers.BrandMapper;
import jp.co.planaria.sample.motocatalog.mappers.MotorcycleMapper;

@Service
public class MotosService {

  @Autowired
  MessageSource messageSource;

  @Autowired
  MotorcycleMapper motorcycleMapper;

  @Autowired
  BrandMapper brandMapper;

  public List<Motorcycle> getMotos(SearchForm condition) {
    return motorcycleMapper.selectByCondition(condition);
  }

  public Motorcycle getMotos(int motoNo) {
    return motorcycleMapper.selectByPK(motoNo);
  }

  public List<Brand> getBrands() {
    return brandMapper.selectAll();
  }

  /**
   * バイク情報を保存する。
   * @param moto バイク情報
   * @return 保存件数
   */
  public int save(Motorcycle moto) {
    if (moto.getMotoNo() == null) {
      // バイク番号がnullなら登録 自分のaddメソッド呼び出して、それぞれの戻り値を返す
      return this.add(moto);
    } else {
      // そうでなければ更新 自分のupdateメソッド呼び出して、それぞれの戻り値を返す
      return this.update(moto);
    }
  
  }

  /**
   * バイク情報を更新する
   * @param moto バイク情報
   * @return 更新件数
   */
  @Transactional
  private int update(Motorcycle moto) {
    int cnt = motorcycleMapper.update(moto);
    // 更新できなかった場合、更新されたか削除されたため楽観的排他エラーとする
    if (cnt == 0) {
      throw new OptimisticLockingFailureException(
          messageSource.getMessage("error.OptimisticLockingFailure",
          null, Locale.JAPANESE));
    }
    // 2件以上更新が想定外（SQLの不備の可能性）
    if (cnt > 1) {
      throw new RuntimeException(
          messageSource.getMessage("error.Runtime",
          new String[] { "2件以上更新されました。" }, Locale.JAPANESE));
    }
    return cnt;
  }

  /**
   * バイク情報を登録する
   * @param moto バイク情報
   * @return 登録件数
   */
  @Transactional
  private int add(Motorcycle moto) {
    // 新しいバイク番号を発行して使う
    Integer motoNo = motorcycleMapper.selectNewMotoNo();
    moto.setMotoNo(motoNo);
    // バイク情報を登録
    int cnt = motorcycleMapper.insert(moto);
    // 登録できなかった場合、登録されたか削除されたため楽観的排他エラーとする
    if (cnt == 0) {
      throw new RuntimeException(
          messageSource.getMessage("error.Runtime",
          new String[] { "登録に失敗しました。" }, Locale.JAPANESE));
    }
    return cnt;
  }
  /**
   * バイク情報を削除する
   * @param moto バイク情報
   * @return 削除件数
   */
  @Transactional
  public int delete(Motorcycle moto) {
    int cnt = motorcycleMapper.delete(moto);
    // 削除できなかった場合、削除されたか削除されたため楽観的排他エラーとする
    if (cnt == 0) {
      throw new OptimisticLockingFailureException(
          messageSource.getMessage("error.OptimisticLockingFailure",
          null, Locale.JAPANESE));
    }
    // 2件以上更新が想定外（SQLの不備の可能性）
    if (cnt > 1) {
      throw new RuntimeException(
          messageSource.getMessage("error.Runtime",
          new String[] { "2件以上削除されました。" }, Locale.JAPANESE));
    }
    return cnt;
  }
}