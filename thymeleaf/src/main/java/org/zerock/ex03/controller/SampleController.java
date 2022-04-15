package org.zerock.ex03.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex03.dto.SampleDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {
	
	@GetMapping("/ex1")
	public void ex1() {
		log.info("ex1.....................");
	}
	@GetMapping("/ex2")
	public void exModel(Model model) {
		log.info("ex2.....................");
		SampleDTO dto = SampleDTO.builder()
								 .regTime(LocalDateTime.now())
								 .build();
		
		List<SampleDTO> list = IntStream.rangeClosed(1,20)
										.asLongStream()
										.mapToObj(i->dto.toBuilder()
														.sno(i)
														.first("first..." + i)
														.last("last..." + i)
														.build())
										.collect(Collectors.toList());
		log.info(list);
		model.addAttribute("list",list);
	}
	
	@GetMapping("/exInline")
	public String exInline(RedirectAttributes rttr) {
		log.info("exInline.......");
		SampleDTO dto = SampleDTO.builder()
								 .sno(100L)
								 .first("First..100")
								 .last("Last..100")
								 .regTime(LocalDateTime.now())
								 .build();
		rttr.addFlashAttribute("result","success");
		rttr.addFlashAttribute("dto",dto);
		return "redirect:/sample/ex3";
	}
	
	@GetMapping("/ex3")
	public void ex3() {
		log.info("ex3");
	}
	
	@GetMapping({"/exLayout1","/exLayout2","/exSidebar","/exTemplate"})
	public void exLayout1() {
		log.info("exLayout.........");
	}
}
