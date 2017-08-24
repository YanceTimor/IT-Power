package com.yance520.itpower.utils;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :常量定义
 */
public class ConstantsUtil {

    //数据库连接
    public abstract class DataSourceCode {
        public static final String DATA_000001 = "DATA_000001";         //主mysql连接池
        public static final String DATA_000002 = "DATA_000002";         //jdbc动态数据源
        public static final String DATA_000003 = "DATA_000003";         //主hana连接池
        public static final String DATA_000004 = "DATA_000004";         //帆软数据源
        public static final String DATA_000005 = "DATA_000005";         //APP-85 数据库
    }

    //请求方式
    public abstract class RequestMethod {
        public static final String GET = "GET";
        public static final String POST = "POST";
    }

    //通用的状态码
    public abstract class CommonCode {
        public static final int SUCCESS_CODE = 0;      //获取数据成功状态码
        public static final int ERROR_CODE = 1;        //获取数据出错状态码
    }

    //通用的消息
    public abstract class CommonMessage {
        public static final String SUCCESS_MESSAGE = "请求数据成功!";     //获取数据失败
        public static final String ERROR_MESSAGE = "请求数据出错!!";      //获取数据出错!
        public static final String INTERNAL_MESSAGE = "内部错误,请联系工程师!!";       //内部错误!
    }

    //自定义错误消息
    public abstract class ExceptionCode {
        public static final int INTERNAL_ERROR = 500;          // 内部错误
        public static final int POWER_ERROR = -100;           // 平台内部通用错误
        public static final int TO_LOGIN = -99;                // 跳转到登录
        public static final int SIGN_ERROR = -98;              // openApi请求sign校验错误
        public static final int NULL_ERROR = -97;              // 没有查到数据
    }

    //获取数据方式
    public abstract class ExecuteType {
        public static final int PROCEDURE = 1;     //存储过程
        public static final int EXECUTESQL = 2;     //自定义sql
        public static final int FROMOTHER = 3;     //第三方系统
    }

    //开放接口数据来源
    public abstract class OpenExecuteType {
        public static final String PROVIDE = "PROVIDE_"; //路由接口编码
        public static final String REP = "REP_";     //平台报表接口编码
    }


    //mongodb数据库
    public abstract class MongoDb {
        public static final String ADMIN = "admin";
        public static final String PLATFORM = "platform";
        public static final String STORE_REPLAY = "store_replay";
    }

    //mongodb集合名称
    public abstract class MongoDbgetCollection {
        public static final String sys_operation_log = "sys_operation_log"; // api操作日志
    }

}


