package generator.config;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 默认的代码生成的配置
 *
 * @author fengshuonan
 * @date 2017-10-28-下午8:27
 */
public class GunsGeneratorConfig extends AbstractGeneratorConfig {
	
	private final String moduleName = "privilege"; //

    @Override
    protected void globalConfig() {
    	
        //生成文件的输出目录【默认 D 盘根目录】
        globalConfig.setOutputDir("/work/invite_manage/yestae-uc-manager/uc-mgr-admin/src/main/java");
        
        //是否覆盖已有文件
        globalConfig.setFileOverride(true);
        
        //是否在xml中添加二级缓存配置
        globalConfig.setEnableCache(false);
        
        //开启 BaseResultMap
        globalConfig.setBaseResultMap(true);
        
        //开启 baseColumnList
        globalConfig.setBaseColumnList(true);
        
        //是否打开输出目录
        globalConfig.setOpen(false);
        
        //开发人员
        globalConfig.setAuthor("chenfeida");
    }

    @Override
    protected void dataSourceConfig() {
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/dev_uc_invite?characterEncoding=utf8");
    }

    @Override
    protected void strategyConfig() {
    	// 表前缀
    	strategyConfig.setTablePrefix(new String[]{"uc_m"});
    	//包含的表名
        strategyConfig.setInclude(new String[]{"uc_m_platform_user"}); //
    	//下划线转驼峰命名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //逻辑删除属性名称
        //strategyConfig.setLogicDeleteFieldName("ifDel");
    }

    @Override
    protected void packageConfig() {
        packageConfig.setParent(null);
        packageConfig.setEntity("com.yestae.user.manage.modular."+moduleName+".persistence.model");
        packageConfig.setMapper("com.yestae.user.manage.modular."+moduleName+".persistence.dao");
        packageConfig.setXml("com.yestae.user.manage.modular."+moduleName+".persistence.dao.mapping");
        packageConfig.setController("com.yestae.user.manage.modular."+moduleName+".controller");
        packageConfig.setService("com.yestae.user.manage.modular."+moduleName+".service");
        packageConfig.setServiceImpl("com.yestae.user.manage.modular."+moduleName+".service.impl");
    }

    @Override
    protected void contextConfig() {
    	//业务名称
        contextConfig.setBizChName("平台用户"); //
        contextConfig.setBizEnName("platformUser"); //
        contextConfig.setModuleName(moduleName);
        contextConfig.setProjectPath("/work/invite_manage/yestae-uc-manager/uc-mgr-admin");
        contextConfig.setEntityName("PlatformUser"); //
        contextConfig.setModelPackageName("com.yestae.user.manage.modular."+moduleName+".persistence.model");
        contextConfig.setModelMapperPackageName("com.yestae.user.manage.modular."+moduleName+".persistence.model.dao");

        /**
         * mybatis-plus 生成器开关
         */
        contextConfig.setEntitySwitch(true);
        contextConfig.setDaoSwitch(true);
        contextConfig.setServiceSwitch(true);

        /**
         * guns 生成器开关
         */
        contextConfig.setControllerSwitch(true);
        contextConfig.setIndexPageSwitch(true);
        contextConfig.setAddPageSwitch(true);
        contextConfig.setEditPageSwitch(true);
        contextConfig.setJsSwitch(true);
        contextConfig.setInfoJsSwitch(true);
    }
}
