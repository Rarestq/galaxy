package com.wuxiu.galaxy.dal;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis-plus 代码自动生成器
 * @author wuxiu
 */
public class MPGenerator {

    /**
     * 表前缀过滤
     */
    public static String tablePrefix = "galaxy_";
    public static final String LINK = "jdbc:mysql://localhost:3306/db_luggage_storage?useUnicode=true&characterEncoding=utf-8";
    public static final String ACCOUNT = "root";
    public static final String PASSWORD = "123123";

    public static String[] tables = {
            "galaxy_admin",
            "galaxy_charge_calculation_rule",
            "galaxy_common_calculate_rule_detail",
            "galaxy_fragile_calculate_rule_detail",
            "galaxy_luggage_lost_compensation_record",
            "galaxy_luggage_lost_registration_record",
            "galaxy_luggage_overdue_record",
            "galaxy_luggage_storage_record",
            "galaxy_pickup_luggage_record",
            "galaxy_luggage_type",
            "galaxy_turnover_record",
            "galaxy_valuable_calculate_rule_detail"
    };

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/galaxy-dal/src/main/java/")
                .setAuthor("MPGenerator")
                .setOpen(false)
                .setFileOverride(true)
                // 不需要ActiveRecord特性的请改为false
                .setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                .setMapperName("%sDao")
                .setServiceName("%sManager")
                .setServiceImplName("%sManagerImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(LINK);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(ACCOUNT);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig packageConfig = new PackageConfig()
                .setParent("com.wuxiu.galaxy.dal")
                .setEntity("domain")
                .setMapper("dao")
                .setService("manager")
                .setServiceImpl("manager.impl");

        mpg.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/xDaoMapper.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/galaxy-dal/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //模板设置
        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("/templates/xDomain")
                .setMapper("/templates/xDao")
                .setService("/templates/xManager")
                .setServiceImpl("/templates/xManagerImpl")
                .setXml(null)
                .setController(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass("com.baomidou.ant.common.BaseEntity")
                .setEntityLombokModel(true)
                .setInclude(tables)
                .setSuperEntityColumns("id")
                .setTablePrefix(tablePrefix)
                .setLogicDeleteFieldName("deleted");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
