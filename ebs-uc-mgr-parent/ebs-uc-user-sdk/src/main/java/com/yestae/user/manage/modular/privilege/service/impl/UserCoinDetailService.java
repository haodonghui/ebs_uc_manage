package com.yestae.user.manage.modular.privilege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.persistence.dao.CoinDetailMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.CoinDetail;
import com.yestae.user.manage.modular.privilege.service.IUserCoinDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Package com.yestae.user.manage.modular.privilege.service
 * @ClassName
 * @Author h_don
 * @Date 2020/3/30 15:59
 */
@Service
public class UserCoinDetailService extends ServiceImpl<CoinDetailMapper, CoinDetail> implements IUserCoinDetailService {

    @Resource
    private CoinDetailMapper coinDetailMapper;

    @Override
    public List<Map<String, Object>> selectCoinDetail(Page<Map<String, Object>> page, Map<String, String> map) {
        if(page != null){
            return coinDetailMapper.selectCoinDetail(page, map);
        }else{
            return coinDetailMapper.selectCoinDetail(map);
        }
    }
}
