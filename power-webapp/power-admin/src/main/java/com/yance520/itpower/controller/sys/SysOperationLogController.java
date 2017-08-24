package com.yance520.itpower.controller.sys;

import com.yance520.itpower.controller.AbstractController;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysVisitLogService;
import com.yance520.itpower.utils.PageUtils;
import com.yance520.itpower.utils.Query;
import com.yance520.itpower.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :
 */
@RestController
@RequestMapping("sysoperationlog")
public class SysOperationLogController extends AbstractController {
    @Autowired
    private SysVisitLogService sysVisitLogService;

    @RequestMapping("/reprtcount")
    @RequiresPermissions("sysoperationlog:reportcount")
    public R reportcount(@RequestParam Map<String,Object> params){

        //查询列表数据
        Query query = new Query(params);

        List<Map<String,Object>> sysOperationLogList = sysVisitLogService.reportcount(query);
        int total = sysVisitLogService.reportTotal(query);

        PageUtils pageUtil = new PageUtils(sysOperationLogList, total, query.getLimit(), query.getPage());

        return R.success().put("page", pageUtil);
    }

    /**
     * 近30天访问日志图表
     */
    @RequestMapping("/visit")
    @RequiresPermissions("sysoperationlog:visit")
    public R visit(@RequestParam Map<String, Object> params) {
        if(null == params.get("start") || null == params.get("end")){
            Date endNext = new DateTime().plusDays(1).withMillisOfDay(0).toDate();
            Date begin = new DateTime(endNext.getTime()).minusDays(30).toDate();
            params.put("endNext", endNext);
            params.put("begin", begin);
        }else{
            String start = params.get("start")+" 00:00:00";
            String end = params.get("end")+" 00:00:00";
            params.put("endNext", end);
            params.put("begin", start);
        }

        List<Map<String, Object>> visit = sysVisitLogService.queryVisit(params);

        return R.success().put("visit", visit);
    }

    /**
     * 当日访各时段问日志图表
     */
    @RequestMapping("/visitData")
    @RequiresPermissions("sysoperationlog:visit")
    public R visitData(@RequestParam Map<String, Object> params) throws Exception{

        if(null == params.get("day") || ("").equals(params.get("day"))){
            Date endNext = new DateTime().plusDays(1).withMillisOfDay(0).toDate();
            Date begin = new DateTime().withMillisOfDay(0).toDate();
            params.put("endNext", endNext);
            params.put("begin", begin);
        }else{
            String sday = params.get("day") +" 00:00:00";
            Calendar c = Calendar.getInstance();
            Date date= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sday);
            c.setTime(date);
            int day=c.get(Calendar.DATE);
            c.set(Calendar.DATE,day+1);
            String dayAfter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
            params.put("endNext", dayAfter);
            params.put("begin", sday);
        }
        List<Map<String, Object>> visitData = sysVisitLogService.queryVisitByData(params);
        return R.success().put("visitData", visitData);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysoperationlog:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysOperationLog> sysOperationLogList = sysVisitLogService.queryList(query);
        int total = sysVisitLogService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysOperationLogList, total, query.getLimit(), query.getPage());

        return R.success().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysoperationlog:info")
    public R info(@PathVariable("id") Integer id) {
        SysOperationLog sysOperationLog = sysVisitLogService.queryObject(id);
        return R.success().put("sysOperationLog", sysOperationLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysoperationlog:save")
    public R save(@RequestBody SysOperationLog sysOperationLog) {
        sysVisitLogService.save(sysOperationLog);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysoperationlog:update")
    public R update(@RequestBody SysOperationLog sysOperationLog) {
        sysVisitLogService.update(sysOperationLog);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysoperationlog:delete")
    public R delete(@RequestBody Integer[] ids) {
        sysVisitLogService.deleteBatch(ids);
        return R.success();
    }
}
