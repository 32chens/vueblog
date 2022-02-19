package com.chenlf.vueblog.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

/**
 * 
 * @author ChenLF
 * @date 2022/02/19 17:41
 **/

@Setter
@Getter
public class CodeGenerator {

    /**
     * mybatisplus代码生成方法
     */
    public static void main(String[] args) {

//        Scanner scan = new Scanner(System.in);

        //数据源设置
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/vueblog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                "root", "root")
                //全局设置（文档注释）
                .globalConfig(builder -> {
                    builder.author("ChenLF") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd")
                            .outputDir(System.getProperty("user.dir")+"\\src\\main\\java"); // 指定输出目录
                })
                //包设置  设置生成的文件位置在哪个包
                .packageConfig(builder -> {
                    builder.parent("com.chenlf") // 设置父包名
                            .moduleName("vueblog") // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"\\src\\main\\resources")); // 设置mapperXml生成路径
                })

                //策略设置
                .strategyConfig((scanner,builder) -> {
                    // 需要生成的表名(一个String的表名 ,任意多个表名（可变长参数）：“user”, “user1”,…这样 ,列表list)
                    builder.addInclude(getTables(scanner.apply("=====================策略配置=======================\n请输入表名，多个英文逗号分隔？所有输入 all")))
                            .addTablePrefix("t_","m_")   //设置表前缀过滤
                            //service策略
                            .serviceBuilder()
                            .formatServiceFileName("%sService") //service文件名
                            .formatServiceImplFileName("%sServiceImpl")
                            //实体类entity策略
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")   //说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()   //属性加上说明
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("modify_time", FieldFill.INSERT_UPDATE)
                            )   //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间

                            //controller策略
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()      //开启RestController
                            //Mapper策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)   //设置继承的父类
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()   //开启@Mapper注解
                            .enableBaseResultMap()  //生成通用的resultMap
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
