package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.CoinDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 益币详情表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-27
 */
public interface CoinDetailMapper extends BaseMapper<CoinDetail> {

    List<Map<String, Object>> selectCoinDetail(@Param("page") Page<Map<String, Object>> page,
                                               @Param("map") Map<String, String> map);

    List<Map<String, Object>> selectCoinDetail(@Param("map") Map<String, String> map);
}
