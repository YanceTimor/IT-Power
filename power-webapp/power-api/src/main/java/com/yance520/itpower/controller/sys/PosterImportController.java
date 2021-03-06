package com.yance520.itpower.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yance520.itpower.utils.poster.PosterImportUtil;
//import com.yance520.itpower.utils.redis.RedisBizUtilApi;
import com.yance520.itpower.annotation.SysOperationLog;
import com.yance520.itpower.model.api.TokenApi;
import com.yance520.itpower.model.sys.PosterImportArea;
import com.yance520.itpower.model.sys.PosterImportGoods;
import com.yance520.itpower.service.sys.PosterImportService;
import com.yance520.itpower.utils.R;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by liuwei on 2017/6/27.
 * 海报导入控制类
 */

@RestController
@RequestMapping("/api/poster")
public class PosterImportController {

    Logger log = Logger.getLogger(this.getClass());

    @Reference
    private PosterImportService posterImportService;

//    @Autowired
//    private RedisBizUtilApi redisBizUtilApi;

    @Autowired
    private PosterImportUtil posterImportUtil;

    /**
     * 允许上传的扩展名
     */
    private static final String[] extensionPermit = {"xls", "xlsx"};

    /**
     * 区域维表导入
     *
     * @param multipartRequest
     * @param response
     * @param token
     * @return
     */
    @SysOperationLog("区域维表导入")
    @RequestMapping(value = "areaImport", method = RequestMethod.POST)
    public R areaImport(MultipartHttpServletRequest multipartRequest, HttpServletResponse response,
                        String token) {
        TokenApi tokenApi = null;
        String jobNumber = null;
        List<Map<String, Object>> areaList = null;
        List<PosterImportArea> excellist = new ArrayList<PosterImportArea>();
        try {
            if (token == null) {
                throw new Exception("token不存在");
            } else {
                String tokenApiJsonStr = "";//redisBizUtilApi.getApiToken(token);
                tokenApi = JSONObject.parseObject(tokenApiJsonStr, TokenApi.class);
                jobNumber = tokenApi.getJobNumber();
            }
            for (Iterator it = multipartRequest.getFileNames(); it.hasNext(); ) {
                String key = (String) it.next();
                MultipartFile imgFile = multipartRequest.getFile(key);
                if (!imgFile.isEmpty()) {
                    String fileName = imgFile.getOriginalFilename();
                    String fileExtension = FilenameUtils.getExtension(fileName);
                    if (!ArrayUtils.contains(extensionPermit, fileExtension)) {
                        return R.error("您选择的文件不是excel");
                    }
                    // 根据文件名判断文件是2003版本还是2007版本
                    boolean isExcel2003 = false;
                    // 验证文件名是否合格
                    if (isExcel2003(fileName)) {
                        isExcel2003 = true;
                    } else if (isExcel2007(fileName)) {
                        isExcel2003 = false;
                    } else {
                        return R.error("您选择的文件类型有误");
                    }
                    // 读取excel里面的数据
                    excellist = getExcelInfo(imgFile.getInputStream(), isExcel2003);
                    for (PosterImportArea item : excellist) {
                        item.setJobNumber(jobNumber);
                    }
                    //保存到临时表
                    posterImportService.insertPosterImportAreaTmp(excellist, jobNumber);
                    //将临时表数据和正式数据进行比对，如有匹配上的需要返回
                    areaList = posterImportService.areaTmpJoinList(jobNumber);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.success(areaList);
    }

    /**
     * 区域导入确认接口
     *
     * @param request
     * @param response
     * @param token
     * @return
     */
    @SysOperationLog("区域导入确认接口")
    @RequestMapping(value = "confirmArea", method = RequestMethod.GET)
    public R areaImport(HttpServletRequest request, HttpServletResponse response,
                        String token) {
        TokenApi tokenApi = null;
        String jobNumber = null;
        try {
            if (token == null) {
                throw new Exception("token不存在");
            } else {
                String tokenApiJsonStr = "";//redisBizUtilApi.getApiToken(token);
                tokenApi = JSONObject.parseObject(tokenApiJsonStr, TokenApi.class);
                jobNumber = tokenApi.getJobNumber();
            }
            //关联临时表，如果是重新导入，先删除
            List<Map<String, Object>> areaList = posterImportService.areaTmpJoinList(jobNumber);
            List<Integer> areaIdList = new ArrayList<Integer>();
            for (Map<String, Object> item : areaList) {
                areaIdList.add((Integer) item.get("id"));
            }
            //删除之前导入的，将临时表的数据导入到正式，清空临时表
            posterImportService.deleteArea(areaIdList, jobNumber);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error("程序出现异常");
        }
        return R.success();
    }


    @SysOperationLog("导入")
    @RequestMapping(value = "goodsImport", method = RequestMethod.POST)
    public R goodsImport(MultipartHttpServletRequest multipartRequest, HttpServletResponse response,
                         String token) {
        TokenApi tokenApi = null;
        String jobNumber = null;
        List<Map<String, Object>> areaList = null;
        List<PosterImportGoods> excellist = new ArrayList<PosterImportGoods>();
        try {
            if (token == null) {
                throw new Exception("token不存在");
            } else {
                String tokenApiJsonStr = "";//redisBizUtilApi.getApiToken(token);
                tokenApi = JSONObject.parseObject(tokenApiJsonStr, TokenApi.class);
                jobNumber = tokenApi.getJobNumber();
            }
            for (Iterator it = multipartRequest.getFileNames(); it.hasNext(); ) {
                String key = (String) it.next();
                MultipartFile imgFile = multipartRequest.getFile(key);
                if (!imgFile.isEmpty()) {
                    String fileName = imgFile.getOriginalFilename();
                    String fileExtension = FilenameUtils.getExtension(fileName);
                    if (!ArrayUtils.contains(extensionPermit, fileExtension)) {
                        return R.error("您选择的文件不是excel");
                    }
                    // 根据文件名判断文件是2003版本还是2007版本
                    boolean isExcel2003 = false;
                    // 验证文件名是否合格
                    if (isExcel2003(fileName)) {
                        isExcel2003 = true;
                    } else if (isExcel2007(fileName)) {
                        isExcel2003 = false;
                    } else {
                        return R.error("您选择的文件类型有误");
                    }
                    // 读取excel里面的数据
                    excellist = getExcelInfo1(imgFile.getInputStream(), isExcel2003);
                    //保存到临时表
                    for (PosterImportGoods item : excellist) {
                        item.setJobNumber(jobNumber);
                    }
                    posterImportService.insertPosterImportGoodsTmp(excellist, jobNumber);
                    //将临时表数据和正式数据进行比对，如有匹配上的需要返回
                    areaList = posterImportService.goodsTmpJoinList(jobNumber);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error("程序出现异常");
        }
        return R.success(areaList);
    }

    @SysOperationLog("confirmGoods")
    @RequestMapping(value = "confirmGoods", method = RequestMethod.GET)
    public R confirmGoods(HttpServletRequest request, HttpServletResponse response,
                          String token) {
        TokenApi tokenApi = null;
        String jobNumber = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            if (token == null) {
                throw new Exception("token不存在");
            } else {
                String tokenApiJsonStr = "";//redisBizUtilApi.getApiToken(token);
                tokenApi = JSONObject.parseObject(tokenApiJsonStr, TokenApi.class);
                jobNumber = tokenApi.getJobNumber();
            }
            //关联临时表，如果是重新导入，先删除
            List<Map<String, Object>> goodsList = posterImportService.goodsTmpJoinList(jobNumber);
            List<Integer> goodsIdList = new ArrayList<Integer>();
            for (Map<String, Object> item : goodsList) {
                goodsIdList.add((Integer) item.get("id"));
            }
            //删除之前导入的，将临时表的数据导入到正式，清空临时表
            list = posterImportService.deleteGoods(goodsIdList, jobNumber);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error("程序出现异常");
        }
        return R.success(list);
    }

    /**
     * 查询区域和对应海报id列表
     *
     * @param request
     * @param response
     * @param area
     * @return
     */
    @SysOperationLog("查询区域和对应海报id列表")
    @RequestMapping(value = "areaList", method = RequestMethod.GET)
    public R areaList(HttpServletRequest request, HttpServletResponse response, String area) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = posterImportService.areaList(area);
            Map<String, Object> map = new HashMap<String, Object>();
            if (area == null) {
                for (Map<String, Object> item : list) {
                    if (item.get("area") != null) {
                        if (map.get(item.get("area")) == null) {
                            map.put((String) item.get("area"), item);
                        }
                    }
                }
                return R.success(map);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error("查询区域列表异常");
        }
        return R.success(list);
    }

    @SysOperationLog("查询区域列表")
    @RequestMapping(value = "goodsList", method = RequestMethod.GET)
    public R goodsList(HttpServletRequest request, HttpServletResponse response, String posterId, String area) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = posterImportService.goodsList(posterId, area);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error("查询区域列表异常");
        }
        return R.success(list);
    }


    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String fileName) {
        return fileName.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String fileName) {
        return fileName.matches("^.+\\.(?i)(xlsx)$");

    }

    public List<PosterImportArea> getExcelInfo(InputStream is, boolean isExcel2003) throws Exception {
        List<PosterImportArea> scoreList = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            // 当excel是2003时
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            // 读取Excel里面客户的信息
            scoreList = posterImportUtil.PosterImportArea(wb);

        } catch (IOException e) {
            throw e;
        }
        return scoreList;
    }

    public List<PosterImportGoods> getExcelInfo1(InputStream is, boolean isExcel2003) throws Exception {
        List<PosterImportGoods> goodsList = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            // 当excel是2003时
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            // 读取Excel里面客户的信息
            goodsList = posterImportUtil.PosterImportGoods(wb);
        } catch (IOException e) {
            throw e;
        }
        return goodsList;
    }
}
