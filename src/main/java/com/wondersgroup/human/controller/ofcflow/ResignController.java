package com.wondersgroup.human.controller.ofcflow;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ResignServant;
import com.wondersgroup.human.dto.ofcflow.ResignServantQueryParam;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.ResignServantService;
import com.wondersgroup.human.vo.ofcflow.ResignVO;

/**
 * 辞职控制类
 * 
 * @ClassName: ResignController
 * @Description: 辞职控制器
 * @author: GP
 * @date: 2018年5月17日上午10:58:25 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("ofcflow/resign")
public class ResignController extends GenericController {

	@Autowired
	ResignServantService resignServantService;

	@Autowired
	ServantService servantService;

	@Autowired
	private FlowRecordService flowRecordService;
	
	// 页面路径--辞职人员列表
	private final String RESIGN_INDEX = "models/ofcflow/resign/index";

	// 页面路径--辞职人员详情
	private final String RESIGN = "models/ofcflow/resign/resign";
	
	// 页面路径--辞职人员流程页面
	private final String RESIGN_FLOW = "models/ofcflow/resign/resignFlow";
	
	// 页面路径--辞职人员信息页面
	private final String RESIGN_LIST = "models/ofcflow/resign/resignList";
	
	// 页面路径--辞职人员信息详情
	private final String RESIGN_DETAIL = "models/ofcflow/resign/resignDetail";
	
	//后置路径
	private final static String postFtpUrl="ofcflow/resign/";
	

	/**
	 * 辞职流程待办列表
	 */
	private final static String RESIGN_EMPLOYPLAN_FLOW = "models/ofcflow/resign/flow";
	/**

	/**
	 * 辞职人员列表
	 * 
	 * @Title: ResignList
	 * @Description: 辞职人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("oragn", x);
		return RESIGN_INDEX;
	}

	/**
	 * 辞职申请页面
	 * 
	 * @Title: resign
	 * @Description: 辞职申请页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/resign")
	public String resign(String servantId, Model model) {
		Servant servant = servantService.get(servantId);
		model.addAttribute("id", servantId);
		model.addAttribute("servant", servant);
		return RESIGN;
	}

	/**
	 * @Title: save
	 * @Description: 辞职保存
	 * @param temp辞职信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ResignServant temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				resignServantService.saveResign(temp,opinion,r);
			} else {
				ResignServant resignServant = resignServantService.load(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp,resignServant);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				resignServantService.saveResign(resignServant,opinion,r);
			}
			result.setSuccess(true);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","ResignServant");
		return RESIGN_EMPLOYPLAN_FLOW;
	}
	
	/**
	 * @Title: resignFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/resignFlow")
	public String resignFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		ResignServant r = resignServantService.get(flow.getBusId());
		Servant s = servantService.get(r.getServant().getId());
		model.addAttribute("resignServant", r);
		model.addAttribute("servant", s);
		return RESIGN_FLOW;
	}
	
	/**
	 * 辞职人员列表
	 * 
	 * @Title: ResignList
	 * @Description: 辞职人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/list")
	public String resignList() {
		return RESIGN_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 辞职申请列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<ResignVO> pageList(ResignServantQueryParam param, Integer limit, Integer page) {
		Page<ResignVO> pageInfo = resignServantService.pageList(param, page, limit);
		return pageInfo;
	}
	
	/**
	 * 辞职人员详情
	 * 
	 * @Title: ResignDetail
	 * @Description: 辞职人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/resignDetail")
	public String resignDetail(String id, Model model) {
		ResignServant r = resignServantService.get(id);
		Servant servant = servantService.get(r.getServant().getId());
		model.addAttribute("servant", servant);
		model.addAttribute("resignServant", r);
		
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		if(x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)){//如果x是人社局
			model.addAttribute("isRenShe", true);
		}else{
			model.addAttribute("isRenShe", false);
		}
		return RESIGN_DETAIL;
	}
	
	/**
	 * 
	 * @Title: downloadResignServantFile 
	 * @Description: 辞职相关文件的下载方法
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return: void
	 */
	@RequestMapping("/downloadResignServantFile")
    public @ResponseBody void downloadResignServantFile(@RequestParam(name="fileName",required=true) String fileName,@RequestParam(name="fileShowName",required=true) String fileShowName,HttpServletResponse response) throws IOException{
	    ServletOutputStream  out = response.getOutputStream();  
        response.setCharacterEncoding("utf-8");  
        response.setContentType("application/msword");  
        // 设置浏览器以下载的方式处理该文件名  
        response.setHeader("Content-Disposition", "attachment;filename="  
                .concat(String.valueOf(URLEncoder.encode(fileShowName, "UTF-8"))));  
	    byte[] ftpDownloadInByte = FtpTool.ftpDownloadInByte("/human/"+postFtpUrl, fileName);
		out.write(ftpDownloadInByte);
    }
	
	/**
	 */
	@RequestMapping("/validate")
	@ResponseBody
	public AjaxResult valiateExistEmployPlan(Model model, String servantId) {
		AjaxResult result = new AjaxResult(true);
		try {
			ResignServant resignServant = resignServantService.getByServantId(servantId);
			if(resignServant!=null){
				throw new BusinessException("该用户已进入流程！");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("发起失败！");
		}
		return result;
	}
}
