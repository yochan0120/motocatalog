package jp.co.planaria.sample.motocatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import jp.co.planaria.sample.motocatalog.beans.Brand;
import jp.co.planaria.sample.motocatalog.beans.Motorcycle;
import jp.co.planaria.sample.motocatalog.beans.SearchForm;
import jp.co.planaria.sample.motocatalog.mappers.BrandMapper;
import jp.co.planaria.sample.motocatalog.mappers.MotorcycleMapper;

@Service
public class MotosService {

  @Autowired
  MotorcycleMapper motorcycleMapper;

  @Autowired
  BrandMapper brandMapper;

  public List<Motorcycle> getMotos(SearchForm condition){
    return motorcycleMapper.selectByCondition(condition);
  }

  public Motorcycle getMotos(int motoNo){
    return motorcycleMapper.selectByPK(motoNo);
  }

  public List<Brand> getBrands() {
    return brandMapper.selectAll();
  }

/**
 * バイク情報を更新する
 * @param moto バイク情報
 * @return 更新件数
 */
  public int save(Motorcycle moto) {
    int cnt = motorcycleMapper.update(moto);
    if(cnt == 0) {
      throw new OptimisticLockingFailureException("楽観的排他エラー");
    }
    return motorcycleMapper.update(moto);
  }
}
