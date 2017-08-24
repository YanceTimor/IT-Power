package com.yance520.itpower.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.controller.AbstractController;
import com.yance520.itpower.model.sys.SysFtpConfig;
import com.yance520.itpower.service.sys.SysFtpConfigService;
import com.yance520.itpower.utils.PageUtils;
import com.yance520.itpower.utils.Query;
import com.yance520.itpower.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 系统FTP配置信息表
 *
 */
@RestController
@RequestMapping("/sys/sysftpconfig")
public class SysFtpConfigController extends AbstractController {
    @Autowired
    private SysFtpConfigService sysFtpConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysftpconfig:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysFtpConfig> sysFtpConfigList = sysFtpConfigService.queryList(query);
        int total = sysFtpConfigService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysFtpConfigList, total, query.getLimit(), query.getPage());

        return R.success().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysftpconfig:info")
    public R info(@PathVariable("id") Long id) {
        SysFtpConfig sysFtpConfig = sysFtpConfigService.queryObject(id);
        return R.success().put("sysFtpConfig", sysFtpConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysftpconfig:save")
    public R save(@RequestBody SysFtpConfig sysFtpConfig) {
        sysFtpConfigService.save(sysFtpConfig);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysftpconfig:update")
    public R update(@RequestBody SysFtpConfig sysFtpConfig) {
        sysFtpConfigService.update(sysFtpConfig);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysftpconfig:delete")
    public R delete(@RequestBody Long[] ids) {
        sysFtpConfigService.deleteBatch(ids);

        for (Long id : ids) {
        }
        return R.success();
    }

}
