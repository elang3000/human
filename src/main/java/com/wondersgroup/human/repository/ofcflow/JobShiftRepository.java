package com.wondersgroup.human.repository.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.JobShift;

import java.util.List;
import java.util.Map;

public interface JobShiftRepository extends GenericRepository<JobShift>{

    public Page<Map> getIndexData(String orgId, String jobChangeType, String name, Integer page, Integer limit);

    /**
     * 获取人员所有正在处理的postid
     * @param servantId
     * @return
     */
    public List<String> getHandledPostIds(String servantId);
}
