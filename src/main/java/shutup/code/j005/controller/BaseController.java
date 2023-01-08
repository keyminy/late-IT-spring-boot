package shutup.code.j005.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	
	@RequestMapping("/")
	public String main(ModelMap modelMap) {
		modelMap.addAttribute("single","하나");
		List<String> multipleList = new ArrayList<>();
		multipleList.add("멀티1");
		multipleList.add("멀티2");
		modelMap.addAttribute("multiple",multipleList);
		return "index";
	}
	
	@RequestMapping("/multiple")
	public String multiple(ModelMap modelMap) {
		return "multiple";
	}
}
