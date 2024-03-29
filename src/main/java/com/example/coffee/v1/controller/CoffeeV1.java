package com.example.coffee.v1.controller;

import com.example.coffee.v1.service.CoffeeV1Service;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/v1")
@Log4j2
public class CoffeeV1 {

    @Autowired
    CoffeeV1Service v1Service;

    @GetMapping("/coffee")
    public String doCoffee(Model model){
        /* 전체 리스트 조회*/
        //List<Map<String,String>> list =  v1Service.doCoffeeList();
        //model.addAttribute("list",list);
       //System.out.println(list);
        return "/v1/coffee";
    }

    @GetMapping("/coffeeAjax")
    @ResponseBody
    public String doCoffeeAjax(Model model){
        return "/v1/coffeeAjax";
    }
    @PostMapping("/coffeeAjaxPost")
    @ResponseBody
    public String doCoffeeAjaxPost(HttpServletRequest request){
        log.info("=== doCoffeePostAjax ===");
        String strName = request.getParameter("name");
        String strStart_date = request.getParameter("start_date");
        String strEnd_date = request.getParameter("end_date");
        String strKind = request.getParameter("kind");
        log.info("strKind : " + strKind);

        /* 전체 리스트 조회 - 오버로딩 */
        List<Map<String,String>> list =  v1Service.doCoffeeList(strStart_date,strEnd_date,strName,strKind);

        JSONArray jsonArray = new JSONArray();
        for(Map<String,String> fMap : list){
            JSONObject resObj = new JSONObject(fMap); //map을 JSON {  } 한 줄 만들어줌
            jsonArray.put(resObj); //JsonArray에 { } 한 줄 씩 array에 넣어준다.
        }
        JSONObject object = new JSONObject();
        object.put("coffee_list",jsonArray);
        return object.toString();
    }

    @PostMapping("/coffee")
    public String doCoffeePost(HttpServletRequest request,Model model){
        String strName = request.getParameter("name");
        String strStart_date = request.getParameter("start_date");
        String strEnd_date = request.getParameter("end_date");
        String strKind = request.getParameter("kind");

        log.info("strKind : " + strKind);


        /* 전체 리스트 조회 - 오버로딩 */
        List<Map<String,String>> list =  v1Service.doCoffeeList(strStart_date,strEnd_date,strName,strKind);
        model.addAttribute("list",list);
        return "/v1/coffee";
    }

    /* 등록하기 Get */
    @GetMapping("/insert")
    public String doInsert() {
        return "/v1/coffee_Ins";
    }

    /* 등록하기 Post, HttpServletRequest 사용 */
    @PostMapping("/insert")
    public String doInsertPost(@RequestParam(value = "name") String name,
                               @RequestParam(value="kind") String kind,
                               @RequestParam(value="price") String price,
                               Model model
                               ) {
        log.info(name + kind + price);
        int intI = v1Service.doInsert(name,kind,price);
        return "redirect:/v1/coffee";
    }

    /* 수정하기 Get, @RequestParam 사용 */
    @GetMapping("/update")
    public String doUpdate(@RequestParam(value="coffee_id") String strCoffee_id,Model model){
        Map<String,String> map = v1Service.doListOne(strCoffee_id); //수정 클릭한 해당 row(한 줄) 가져오기
        model.addAttribute("map",map);
        return "/v1/coffee_Up";
    }
    /* 수정하기 POST, @RequestParam 사용 */
    @PostMapping("/update")
    public String doUpdate(@RequestParam(value="coffee_id") String strCoffee_id,
                           @RequestParam(value="name") String strName,
                           @RequestParam(value="kind") String strKind,
                           @RequestParam(value="price") String strPrice
        ){
       int intI = v1Service.doUpdate(strCoffee_id,strName,strKind,strPrice);
       return "redirect:/v1/coffee";
    }

    /* 1row 삭제하기 , @RequestParam 사용 */
    @GetMapping("/delete")
    public String doDelete(@RequestParam(value="coffee_id") String strCoffee_id){
        int intI = v1Service.doDelete(strCoffee_id);
        return "redirect:/v1/coffee";
    }


}
