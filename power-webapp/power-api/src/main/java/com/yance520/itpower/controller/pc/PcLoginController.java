package com.yance520.itpower.controller.pc;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yance520.itpower.model.paltform.User;
import com.yance520.itpower.service.platform.ApiUserService;
import com.yance520.itpower.utils.HttpContextUtils;
import com.yance520.itpower.utils.IPUtils;
import com.yance520.itpower.utils.Md5Util;
import com.yance520.itpower.utils.R;
import com.yance520.itpower.annotation.IgnoreAuth;
import com.yance520.itpower.model.api.TokenApi;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.TokenApiService;
import com.yance520.itpower.service.sys.SysoperationLogService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import com.yance520.itpower.utils.redis.ReportUtil;
import com.yance520.itpower.utils.redis.TokenUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户登陆
 * <p>
 */
@Api(value = "PC统一登录入口", description = "PC统一登录入口")
@RestController
@RequestMapping("/api/pc")
public class PcLoginController {

    Logger log = Logger.getLogger(this.getClass());

    @Reference
    private ApiUserService apiUserService;
    @Reference
    private TokenApiService tokenApiService;
    @Reference
    private SysoperationLogService sysoperationLogService;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ReportUtil reportUtil;

    /**
     * 用户登陆操作
     *
     * @param request
     * @param password
     * @param userName
     * @return TokenApi 只返回必要字段，不得返回用户密码和敏感信息
     */
    public R login(HttpServletRequest request, String password, String userName) {
        //设置用户操作日志对象
        SysOperationLog logInfo = new SysOperationLog();
        logInfo.setRemark("login");
        try {
            //获取用户ip,url.参数
            logInfo.setStartTime(new Date());
            logInfo.setMethod(request.getMethod());
            IPUtils iputil = new IPUtils();
            logInfo.setIp(iputil.getIpAddr(request));
            logInfo.setUrl(request.getRequestURL().toString());
            logInfo.setParameter(HttpContextUtils.getParameterForLog(request));

            if (userName != null) {
                logInfo.setUserName(userName);
                //根据userName查询用户
                User user = apiUserService.checkUser(userName);
                if (user == null) {
                    return R.error("该用户账号暂未注册");
                }
                if (user.getRoleIdList()==null){
                    return R.error("角色权限未分配");
                }
                if (!StringUtils.isEmpty(password)) {
                    //验证密码（登陆操作）
                    String pass = Md5Util.getMd5("MD5", 0, null, password);

                    user = apiUserService.login(userName, pass);
                    if (user == null) {
                        return R.error("用户名或密码错误");
                    }
                    //生成token
                    TokenApi tokenApiLast = tokenApiService.queryByUserName("80715104");
                    TokenApi tokenApiNew = tokenUtil.createToken(tokenApiLast, user);

                    log.info("登陆日志：" + "用户名＝" + user.getUserName() + "昵称＝" + user.getNickName() + "姓名＝"
                            + user.getTrueName() + "token＝" + tokenApiNew.getToken());
                    return R.success().put("user", tokenApiNew);
                } else {
                    return R.error("密码为空");
                }
            } else {
                return R.error("用户名为空");
            }
        } catch (Exception e) {
            logInfo.setStatus(1);
            logInfo.setError(StringUtils.substring(e.toString(), 0, 2000));
            return R.error("登录错误");
        } finally {
            logInfo.setEndTime(new Date());
            sysoperationLogService.SaveLog(logInfo);
        }
    }

    /**
     * 用户登出
     *
     * @param request
     */
    @RequestMapping(value = "/loginout", method = RequestMethod.POST)
    public void loginout(HttpServletRequest request, String token) {
        //先从header里面找，在找request，最后参数
        if (StringUtils.isEmpty(token)) {
            // 从header中获取token

            token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                // 从参数中获取token
                token = request.getParameter("token");
            }
        }
        if (!StringUtils.isEmpty(token)) {
            // 删除用token信息
            //    redisBizUtilApi.removeApiToken(token);
                  tokenApiService.deleteByToken(token);
        }
    }

    /**
     * 测试登陆产生token信息
     * http://localhost:8080/login
     *
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/loginTest")
    public R loginTest() {
//        User user = new User();
//        user.setJobNumber("CS123456");
//        user.setName("测试");
//        user.setRoleId(1);
//        TokenApi tokenApiLast = tokenApiService.queryByJobNumber(user.getJobNumber());
//        TokenApi tokenApiNew = tokenUtil.createToken(tokenApiLast, user);

        return R.success("tokenApiNew").put("last", "tokenApiLast");
    }


    /**
     * token测试
     *
     * @param request
     */
    @IgnoreAuth
    @RequestMapping(value = "tokenTest")
    public R tokenTest(HttpServletRequest request) {
        User user = new User();
        user.setUserName("CS123456");
        user.setNickName("测试");
        //生成token
        TokenApi tokenApiNew = tokenUtil.createToken(null, user);
        return R.success(tokenApiNew);
    }

}
