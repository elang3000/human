package com.wondersgroup.human.controller.analysis;

import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.service.analysis.GeneralCountService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.vo.organization.OrgFormationCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GeneralCountController
 * @Description: 通用统计controller
 * @author: youyd
 * @date: 2018年10月29日 下午2:56:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("/analysis/generalCount")
public class GeneralCountController extends GenericController {
    private static final String KEY_ = "";
    @Autowired
    private OrgFormationService orgFormationService;

    @Resource
    private GeneralCountService generalCountService;

    //通用统计首页
    private static final String GENERAL_COUNT_INDEX = "models/analysis/generalCount/generalCountIndex";

    //通用统计单位页面
    private static final String GENERAL_COUNT_UNIT_PAGE = "models/analysis/generalCount/generalCountUnitPage";

    //通用统计首页
    @RequestMapping("/index")
    public String index() {
        return GENERAL_COUNT_INDEX;
    }

    @RequestMapping("/indexData")
    @ResponseBody
    public Page<OrgFormationCountVO> indexData(Model model, String orgName, Integer limit, Integer page) {
        Page<OrgFormation> list = this.orgFormationService.getOrgFormationByName(orgName, limit, page);
        List<OrgFormationCountVO> listVO = new ArrayList<>();
        for (OrgFormation orgFormation : list.getResult()) {
            OrgFormationCountVO orgFormationVO = new OrgFormationCountVO(orgFormation);
            listVO.add(orgFormationVO);
        }
        Page<OrgFormationCountVO> pageVO = new Page<>(list.getStart(), list.getCurrentPageSize(),
                list.getTotalSize(), list.getPageSize(), listVO);
        return pageVO;
    }

    //通用统计单位页面
    @RequestMapping("/unitPage/{orgId}")
    public String unitPage(@PathVariable(value = "orgId", required = true) String id, Model model) {
        model.addAttribute("orgId", id);
        return GENERAL_COUNT_UNIT_PAGE;
    }

    /**
     * 获取单位图表数据
     *
     * @param orgId
     * @return
     */
    @RequestMapping("/chartData/{type}/{orgId}")
    @ResponseBody
    public Map<String, Object> getSexData(@PathVariable(value = "type", required = true) String type, @PathVariable(value = "orgId", required = true) String orgId) {
        List<Map<String, Object>> mapData = null;
        if (type.equals("sex")) {
            mapData = generalCountService.getSexMapData(orgId);
        } else if (type.equals("nation")) {
            mapData = generalCountService.getNationMapData(orgId);
        } else if (type.equals("education")) {
            mapData = generalCountService.getEducationMapData(orgId);
        } else if (type.equals("degree")) {
            mapData = generalCountService.getDegreeMapData(orgId);
        } else if (type.equals("joblevel")) {
            mapData = generalCountService.getJobLevelMapData(orgId);
        } else if (type.equals("post")) {
            mapData = generalCountService.getPostMapData(orgId);
        } else {
            throw new BusinessException("请求路径有误!");
        }
        List<String> nameList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        for (Map<String, Object> map : mapData) {
            nameList.add((String) map.get("NAME"));
            valueList.add(((BigDecimal) map.get("VALUE")).intValue());
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("nameList", nameList);
        resultMap.put("valueList", valueList);
        resultMap.put("data", mapData);
        return resultMap;
    }


}
