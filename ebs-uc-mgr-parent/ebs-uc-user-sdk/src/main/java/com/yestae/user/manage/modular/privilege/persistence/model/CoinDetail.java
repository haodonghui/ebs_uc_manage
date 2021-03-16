package com.yestae.user.manage.modular.privilege.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 益币详情表
 * </p>
 *
 * @author jobob
 * @since 2019-11-27
 */
@Data
@TableName("yestae_coin_detail")
@Slf4j
public class CoinDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户userId
     */
    private Long userId;

    /**
     * 流水编号
     */
    private String flowNo;

    /**
     * 平台订单业务编号
     */
    private String orderNo;

    /**
     * 所属产品
     */
    private Integer product;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 益元币变动方向:1-入账 2-出帐
     */
    private String direction;

    /**
     * 益币业务类型 1-订单交易完成 2-订单取消 3-订单退款 4-注册 5-签到 6-抽奖
     */
    private String type;

    /**
     * 变更金额
     */
    private BigDecimal amount;

    /**
     * 交易前余额
     */
    private BigDecimal preAmount;

    /**
     * 交易后余额
     */
    private BigDecimal laterAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 失效时间
     */
    private Long invalidTime;
}
