package com.yance520.itpower.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.annotation.SysLog;
import com.yance520.itpower.controller.AbstractController;
import com.yance520.itpower.model.sys.SysConfig;
import com.yance520.itpower.service.sys.SysConfigService;
import com.yance520.itpower.utils.PageUtils;
import com.yance520.itpower.utils.Query;
import com.yance520.itpower.utils.R;
import com.yance520.itpower.utils.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
    @Autowired
    private SysConfigService sysConfigService;
//    @Autowired
//    private RedisBizUtilAdmin redisBizUtilAdmin;

    /**
     * 所有配置列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysConfig> configList = sysConfigService.queryList(query);
        int total = sysConfigService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());

        return R.success().put("page", pageUtil);
    }


    /**
     * 配置信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public R info(@PathVariable("id") Long id) {
        SysConfig config = sysConfigService.queryObject(id);

        return R.success().put("config", config);
    }

    /**
     * 保存配置
     */
    @SysLog("保存配置")
    @RequestMapping("/save")
    @RequiresPermissions("sys:config:save")
    public R save(@RequestBody SysConfig config) {
        ValidatorUtils.validateEntity(config);
        sysConfigService.save(config);
        return R.success();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @RequestMapping("/update")
    @RequiresPermissions("sys:config:update")
    public R update(@RequestBody SysConfig config) {
        ValidatorUtils.validateEntity(config);
        sysConfigService.update(config);
        return R.success();
    }

    /**
     * 删除配置
     */
    @SysLog("删除配置")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public R delete(@RequestBody String[] keys) {
        sysConfigService.deleteBatch(keys);
        return R.success();
    }

    //一键缓存
    @RequestMapping("/addRedis")
    public R addRedis(@RequestBody String[] keys) {
        for (String key : keys) {
            SysConfig sysConfig = sysConfigService.queryObject(key);
        }
        return R.success();
    }
}
