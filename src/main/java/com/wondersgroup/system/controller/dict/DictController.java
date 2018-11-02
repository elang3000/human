/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DictController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.dict
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年8月4日 上午3:56:48
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年8月4日 上午3:56:48
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.system.controller.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.bo.CodeType;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: DictController
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年8月4日 上午3:56:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("system/dict")
public class DictController extends GenericController {
	
	private final String DICT_TYPE_INDEX = "dict/queryCodtTypeIndex",
	        DICT_TYPE_CREATE_INDEX = "dict/createCodeTypeIndex", DICT_TYPE_EDIT_INDEX = "dict/editCodeTypeIndex",
	        DICT_INFO_INDEX = "dict/queryCodeInfoIndex", DICT_INFO_EDIT_INDEX = "dict/editCodeInfoIndex",
	        DICT_INFO_CREATE_INDEX = "dict/createCodeInfoIndex";
	
	@RequestMapping("/type/index")
	public String codeTypeIndex() {
		
		return DICT_TYPE_INDEX;
	}
	
	@RequestMapping("/type/page")
	@ResponseBody
	public Page<CodeType> queryCodeType(String code, String name, Integer limit, Integer page) {
		
		return getDictableService().queryCodeType(code, name, limit, page);
	}
	
	@RequestMapping("/type/create")
	public String createCodeTypeIndex() {
		
		return DICT_TYPE_CREATE_INDEX;
	}
	
	@RequestMapping("/type/create/save")
	@ResponseBody
	public AjaxResult saveCreateCodeType(CodeType codeType) {
		
		try {
			codeType.setCreater(SecurityUtils.getUserId());
			if (getDictableService().addCodeType(codeType)) {
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
			} else {
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.error"));
			}
		} catch (Exception ex) {
			logger.error("维护数据字典类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/type/edit")
	public String editCodeTypeIndex(String id, Model model) {
		
		CodeType codeType = getDictableService().loadCodeTypeById(id);
		model.addAttribute("type", codeType);
		return DICT_TYPE_EDIT_INDEX;
	}
	
	@RequestMapping("/type/edit/save")
	@ResponseBody
	public AjaxResult saveEditCodeTypeIndex(CodeType type) {
		
		try {
			type.setLastOperator(SecurityUtils.getUserId());
			getDictableService().updateCodeType(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护数据字典类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/type/remove/save")
	@ResponseBody
	public AjaxResult removeCodeType(String id) {
		
		try {
			getDictableService().removeCodeType(id);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护数据字典类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/info/index")
	public String queryCodeInfoIndex(String codeTypeId, Model model) {
		
		CodeType type = getDictableService().loadCodeTypeById(codeTypeId);
		model.addAttribute("type", type);
		List<CodeInfo> infos = getDictableService().findCodeInfoByCodeType(type.getCode());
		if (!infos.isEmpty()) {
			model.addAttribute("info", infos.get(0));
		} else {
			model.addAttribute("info", new CodeInfo());
		}
		return DICT_INFO_INDEX;
	}
	
	@RequestMapping("/info/load")
	public String loadCodeInfo(String codeTypeId, String id, Model model) {
		
		if (StringUtils.isNotBlank(id)) {
			CodeInfo codeInfo = getDictableService().loadWithLazy(id, "parent", "codeType");
			if (codeInfo != null) {
				model.addAttribute("info", codeInfo);
			} else {
				model.addAttribute("info", new CodeInfo());
			}
		} else {
			model.addAttribute("info", new CodeInfo());
		}
		model.addAttribute("codeTypeId", codeTypeId);
		return DICT_INFO_EDIT_INDEX;
	}
	
	@RequestMapping("/info/query")
	@ResponseBody
	public List<HashMap<String, Object>> queryCodeInfoList(String codeTypeId, String id) {
		
		CodeType type = getDictableService().loadCodeTypeById(codeTypeId);
		List<CodeInfo> codeInfos = null;
		if (StringUtils.isNotBlank(id)) {
			CodeInfo codeInfo = getDictableService().loadCodeInfoById(id);
			codeInfos = getDictableService().findCodeInfoByCodeType(type.getCode(), codeInfo.getCode());
		} else {
			codeInfos = getDictableService().findCodeInfoByCodeType(type.getCode(), null);
		}
		
		List<HashMap<String, Object>> treeData = new ArrayList<HashMap<String, Object>>();
		for (CodeInfo codeInfo : codeInfos) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", codeInfo.getId());
			map.put("name", codeInfo.getName());
			if (StringUtils.isNotBlank(id)) {
				map.put("parentId", id);
			} else {
				map.put("parentId", null);
			}
			map.put("codeTypeCode", type.getCode());
			treeData.add(map);
		}
		return treeData;
	}
	
	@RequestMapping("/info/edit/save")
	@ResponseBody
	public AjaxResult saveEditCodeInfo(CodeInfo codeInfo) {
		
		try {
			CodeInfo targetCodeInfo = getDictableService().loadWithLazy(codeInfo.getId(), "codeType");
			BeanUtils.copyPropertiesIgnoreNull(codeInfo, targetCodeInfo);
			getDictableService().updateCodeInfo(codeInfo, targetCodeInfo.getCodeType());
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护数据字典", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/info/remove/save")
	@ResponseBody
	public AjaxResult saveRemoveCodeInfo(String id) {
		
		try {
			CodeInfo codeInfo = getDictableService().loadWithLazy(id, "codeType");
			getDictableService().removeCodeInfo(id, codeInfo.getCodeType());
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护数据字典", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/info/create")
	public String createCodeInfoIndex(String codeTypeId, String parentId, Model model) {
		
		if (StringUtils.isNotBlank(parentId)) {
			CodeInfo codeInfo = getDictableService().loadCodeInfoById(parentId);
			model.addAttribute("parent", codeInfo);
		} else {
			model.addAttribute("parent", null);
		}
		model.addAttribute("codeTypeId", codeTypeId);
		return DICT_INFO_CREATE_INDEX;
	}
	
	@RequestMapping("/info/create/save")
	@ResponseBody
	public AjaxResult saveCreateCodeInfo(CodeInfo codeInfo, String parentId, String codeTypeId) {
		
		try {
			
			if (StringUtils.isNotBlank(parentId)) {
				CodeInfo parentCodeInfo = getDictableService().loadCodeInfoById(parentId);
				codeInfo.setParent(parentCodeInfo);
			}
			CodeType codeType = getDictableService().loadCodeTypeById(codeTypeId);
			codeInfo.setCreater(SecurityUtils.getUserId());
			getDictableService().addCodeInfoWithType(codeInfo, codeType.getCode());
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护数据字典", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
		
	}
}
