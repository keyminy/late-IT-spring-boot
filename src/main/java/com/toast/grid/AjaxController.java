package com.toast.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("ajax")
public class AjaxController {
	
	@RequestMapping("toastList")
	public List toastList() {
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("name","a");
		map.put("artist","b");
		map.put("type","c");
		map.put("release","1.1.1.0");
		map.put("genre","M");
		list.add(map);
		list.add(map);
		list.add(map);
		list.add(map);
		list.add(map);
		list.add(map);
		list.add(map);
		return list;
	}
	
	@RequestMapping("toastDataList")
	public List toastDataList() {
		List list = new ArrayList();
		Data data = new Data("a","b","c","1.1.1.0","M");
		list.add(data);
		data = new Data("d","e","f","1.1.1.2","W");
		list.add(data);
		data = new Data("g","h","i","1.1.1.3","M");
		list.add(data);
		data = new Data("j","k","l","1.1.1.4","W");
		list.add(data);
		return list;
	}
}

@Getter
@Setter
@AllArgsConstructor
class Data{
	private String name;
	private String artist;
	private String type;
	private String release;
	private String genre;
}