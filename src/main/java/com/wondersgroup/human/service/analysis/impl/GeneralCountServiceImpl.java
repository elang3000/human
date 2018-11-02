package com.wondersgroup.human.service.analysis.impl;

import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.repository.ofc.ServantRepository;
import com.wondersgroup.human.service.analysis.GeneralCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GeneralCountServiceImpl implements GeneralCountService {


    @Autowired
    ServantRepository servantRepository;

    @Autowired
    DictableService dictableService;

    @Override
    public List<Map<String, Object>> getSexMapData(String orgId) {
        return this.servantRepository.getSexMapData(orgId);
    }

    @Override
    public List<Map<String, Object>> getNationMapData(String orgId) {
        return this.servantRepository.getNationMapData(orgId);
    }

    @Override
    public List<Map<String, Object>> getEducationMapData(String orgId) {
        return this.servantRepository.getEducationMapData(orgId);
    }

    @Override
    public List<Map<String, Object>> getDegreeMapData(String orgId) {
        return this.servantRepository.getDegreeMapData(orgId);
    }

    /**
     * 获取职级图表数据
     *
     * @param orgId
     * @return
     */
    @Override
    public List<Map<String, Object>> getJobLevelMapData(String orgId) {
        return this.servantRepository.getJobLevelMapData(orgId);
    }

    /**
     * 获取职级图表数据
     *
     * @param orgId
     * @return
     */
    @Override
    public List<Map<String, Object>> getPostMapData(String orgId) {
        return this.servantRepository.getPostMapData(orgId);
    }


}
