package com.wondersgroup.widget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToolTreeController {

	@RequestMapping("toolTree/staticIndex/{ruleKey}")
	public String staticIndex(Model model,@PathVariable("ruleKey")String ruleKey) {
		model.addAttribute("ruleKey", ruleKey);
		return "widget/tree/staticIndex";
	}
	
	@RequestMapping("toolTree/dynamicIndex/{ruleKey}")
	public String dynamicIndex(Model model,@PathVariable("ruleKey")String ruleKey) {
		model.addAttribute("ruleKey", ruleKey);
		return "widget/tree/dynamicIndex";
	}
	
	@RequestMapping("toolTree/customDynamicIndex")
	public String customDynamicIndex(Model model,String url) {
		model.addAttribute("url", url);
		return "widget/tree/customDynamicIndex";
	}
}
