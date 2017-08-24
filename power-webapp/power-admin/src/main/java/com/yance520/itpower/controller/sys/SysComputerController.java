package com.yance520.itpower.controller.sys;

import com.yance520.itpower.controller.AbstractController;
import com.yance520.itpower.model.sys.SysCompterEntity;
import com.yance520.itpower.utils.GetComputer;
import com.yance520.itpower.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统信息
 */
@RestController
@RequestMapping("/sys/computer")
public class SysComputerController extends AbstractController {

    /**
     * 配置信息
     */
    @RequestMapping("/info")
    public R info() {
        SysCompterEntity computerInfo = new SysCompterEntity();
        computerInfo.setSysTotalPhysicalMemorySize(GetComputer.getTotalPhysicalMemorySize());
        computerInfo.setSysFreePhysicalMemorySize(GetComputer.getFreePhysicalMemorySize());
        computerInfo.setAvailableProcessors(GetComputer.getAvailableProcessors());

        computerInfo.setMaxControl(GetComputer.getMaxControl());
        computerInfo.setCurrentUse(GetComputer.getCurrentUse());
        computerInfo.setFreeMemory(GetComputer.getFreeMemory());
        return R.success().put("computerInfo", computerInfo).put("properties", GetComputer.getProperties());
    }

}
