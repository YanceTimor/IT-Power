package com.yance520.itpower.mapper.sys;

import com.yance520.itpower.model.sys.PosterImportArea;
import com.yance520.itpower.model.sys.PosterImportGoods;

import java.util.List;
import java.util.Map;

/**
 * 海报导入
 */
public interface PosterImportMapper {

    List<Map<String, Object>> areaTmpJoinList(String jobNumber);

    List<Map<String, Object>> areaList();

    List<Map<String, Object>> tmpAreaList(String jobNumber);

    List<Map<String, Object>> tmpGoodsList(String jobNumber);

    List<Map<String, Object>> goodsList(String posterId, String area);

    //通过区域查询对应的列表
    List<Map<String, Object>> listByArea(String area);

    //查询国家
    int queryCountry(String country);

   int queryVender(String venderid);

    //区域和城市对应列表
    List<Map<String, Object>> areamansList();

    int insertPosterImportArea(List<PosterImportArea> list);

    int insertPosterImportAreaTmp(List<PosterImportArea> list);

    int deleteArea(List<Integer> list);

    int insertArea();

    int deleteAreaTmp(String jobNumber);

    //根据jobNumber查询商品海报重复导入吻合的数据
    List<Map<String, Object>> goodsTmpJoinList(String jobNumber);

    //将商品海报保存到临时表
    int insertPosterImportGoodsTmp(List<PosterImportGoods> list);

    //根据id删除正式表已存在的海报商品信息
    int deleteGoods(List<Integer> list);

    //将数据从临时表导入正式表
    int insertGoods();

    //根据jobnumber，删除临时表里面的数据
    int deleteGoodsTmp(String jobNumber);
}
