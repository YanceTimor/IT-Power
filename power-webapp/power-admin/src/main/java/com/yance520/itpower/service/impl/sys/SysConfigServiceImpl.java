package com.yance520.itpower.service.impl.sys;

import com.yance520.itpower.mapper.sys.SysConfigMapper;
import com.yance520.itpower.model.sys.SysConfig;
import com.yance520.itpower.service.sys.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * rpc服务提供者 参数配置
 * <p>
 * Created by 张海 on 2017/04/29.
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;

    public void save(SysConfig config) {
        sysConfigMapper.save(config);
    }

    public void update(SysConfig config) {
        sysConfigMapper.update(config);
    }

    public void updateValueByKey(String key, String value) {
        sysConfigMapper.updateValueByKey(key, value);
    }

    public void deleteBatch(String[] ids) {
        sysConfigMapper.deleteBatch(ids);
    }

    public List<SysConfig> queryList(Map<String, Object> map) {
        return sysConfigMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return sysConfigMapper.queryTotal(map);
    }

    public SysConfig queryObject(Long id) {
        return sysConfigMapper.queryObject(id);
    }

    public SysConfig queryObject(String key){
        return sysConfigMapper.queryObject(key);
    }

    public String getValue(String key, String defaultValue) {
        String value = sysConfigMapper.queryByKey(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

}
