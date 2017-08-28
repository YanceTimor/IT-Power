package com.yance520.itpower.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 */
@Controller
public class SysPageController {

    /**
     * 系统管理页面
     *
     * @param url
     * @return
     */
    @RequestMapping("sys/{url}.html")
    public String page(@PathVariable("url") String url) {
        return "sys/" + url + ".html";
    }

    @RequestMapping("platform/{url}.html")
    public String platform(@PathVariable("url") String url) {
        return "platform/" + url + ".html";
    }

    @RequestMapping("error/{url}.html")
    public String app(@PathVariable("url") String url) {
        return "error/" + url + ".html";
    }

//    @RequestMapping("businessman/{url}.html")
//    public String businessman(@PathVariable("url") String url) {
//        return "businessman/" + url + ".html";
//    }

//    @RequestMapping("reportmodulepage/{url}.html")
//    public String reportmodulepage(@PathVariable("url") String url) {
//        return "reportmodulepage/" + url + ".html";
//    }

    /**
     * 通用页面
     *
     * @param url
     * @return
     */
    @RequestMapping("{url}/{url}.html")
    public String bamboo(@PathVariable("url") String url) {
        return url + "/" + url + ".html";
    }
}
