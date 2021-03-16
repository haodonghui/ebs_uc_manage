package com.yestae.user.manage.config;

import java.sql.SQLException;
import java.util.HashMap;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.yestae.user.manage.core.mutidatasource.config.CoinDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.yestae.user.manage.common.constant.DSEnum;
import com.yestae.user.manage.core.datascope.DataScopeInterceptor;
import com.yestae.user.manage.core.datasource.DruidProperties;
import com.yestae.user.manage.core.mutidatasource.DynamicDataSource;
import com.yestae.user.manage.core.mutidatasource.config.MutiDataSourceProperties;

import javax.annotation.Resource;

/**
 * MybatisPlus配置
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@MapperScan(basePackages = {"com.yestae.user.manage.modular.*.dao", "com.yestae.user.manage.modular.*.persistence.dao"})
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;

    @Resource
    CoinDataSourceProperties coinDataSourceProperties;

    /**
     * yestae-uc的数据源
     */
    private DruidDataSource ucDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        mutiDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * yestae-uc-mgr的数据源
     */
    private DruidDataSource ucMgrDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * coin数据源
     */
    private DruidDataSource coinDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        coinDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "uc-mgr", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
    	
        return ucMgrDataSource();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "uc-mgr", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {
    	
        DruidDataSource ucMgrDataSource = ucMgrDataSource();
        DruidDataSource ucDataSource = ucDataSource();
        DruidDataSource coinDataSource = coinDataSource();

        try {
        	ucMgrDataSource.init();
        	ucDataSource.init();
            coinDataSource.init();
        }catch (SQLException sql){
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(DSEnum.DATA_SOURCE_UC_MGR, ucMgrDataSource);
        hashMap.put(DSEnum.DATA_SOURCE_UC, ucDataSource);
        hashMap.put(DSEnum.DATA_SOURCE_COIN, coinDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(ucMgrDataSource);
        return dynamicDataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }
}
