package com.yance520.itpower.service.impl.sys;

import com.yance520.itpower.mapper.sys.SysFtpConfigMapper;
import com.yance520.itpower.model.sys.SysFtpConfig;
import com.yance520.itpower.service.sys.SysFtpConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysFtpConfigService")
public class SysFtpConfigServiceImpl implements SysFtpConfigService {
    @Autowired
    private SysFtpConfigMapper sysFtpConfigMapper;

    @Override
    public SysFtpConfig queryObject(Long id) {
        return sysFtpConfigMapper.queryObject(id);
    }

    @Override
    public List<SysFtpConfig> queryList(Map<String, Object> map) {
        return sysFtpConfigMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysFtpConfigMapper.queryTotal(map);
    }

    @Override
    public void save(SysFtpConfig sysFtpConfig) {
        sysFtpConfigMapper.save(sysFtpConfig);
    }

    @Override
    public void update(SysFtpConfig sysFtpConfig) {
        sysFtpConfigMapper.update(sysFtpConfig);
    }

    @Override
    public void delete(Long id) {
        sysFtpConfigMapper.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysFtpConfigMapper.deleteBatch(ids);
    }

}
