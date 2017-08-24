package com.yance520.itpower.service.sys;

import com.yance520.itpower.model.sys.SysFtpConfig;

import java.util.List;
import java.util.Map;

/**
 * Author : 杨杨
 * Date : 2017/08/21
 * Description :
 */
public interface SysFtpConfigService {

    SysFtpConfig queryObject(Long id);

    List<SysFtpConfig> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysFtpConfig sysFtpConfig);

    void update(SysFtpConfig sysFtpConfig);

    void delete(Long id);

    void deleteBatch(Long[] ids);

}
