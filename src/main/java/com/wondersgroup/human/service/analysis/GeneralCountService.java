package com.wondersgroup.human.service.analysis;

import java.util.List;
import java.util.Map;

/**
 * 通用统计service
 */
public interface GeneralCountService {

    /**
     * 获取性别图表数据
     * @param orgId
     * @return
     */
    public List<Map<String,Object>> getSexMapData(String orgId);

    /**
     * 获取民族图表数据
     * @param orgId
     * @return
     */
    public List<Map<String,Object>> getNationMapData(String orgId);

    /**
     * 获取学历图表数据
     * @param orgId
     * @return
     */
    public List<Map<String, Object>> getEducationMapData(String orgId);

    /**
     * 获取最高学位图表数据
     * @param orgId
     * @return
     */
    public List<Map<String, Object>> getDegreeMapData(String orgId);
    /**
     * 获取职级图表数据
     * @param orgId
     * @return
     */
    public List<Map<String, Object>> getJobLevelMapData(String orgId);

    /**
     * 获取职级图表数据
     * @param orgId
     * @return
     */
    public List<Map<String, Object>> getPostMapData(String orgId);



}
