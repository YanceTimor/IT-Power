package com.yance520.itpower;

import com.alibaba.dubbo.container.Main;
import com.yance520.itpower.service.test.TestDataSourceService;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 调用服务
 */
public class ApiServerApplication {

    private Logger log = Logger.getLogger(this.getClass());

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springMVC-servlet.xml");

        // 接口测试、数据源测试
        TestDataSourceService testDataSourceService = (TestDataSourceService) context.getBean("dataSourceService");
        System.out.println(testDataSourceService.dataA());
        System.out.println(testDataSourceService.dataB());
        System.out.println(testDataSourceService.dataC());
        System.out.println(testDataSourceService.dataD());
        System.out.println(testDataSourceService.dataE());
        System.out.println(testDataSourceService.dataF());

        Main.main(args);
    }

}
