package com.example.coffee.v2.controller;

import com.example.coffee.v2.service.CoffeeV2Service;
import com.example.coffee.v2.vo.VoCoffeeV2;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v2")
@Log4j2
public class CoffeeV2 {

    @Autowired
    CoffeeV2Service v2Service;

    @GetMapping("/coffee")
    public String doCoffee(Model model){
        /* 전체 리스트 조회*/
        //List<Map<String,String>> list =  v2Service.doCoffeeList();
        //model.addAttribute("list",list);
       //System.out.println(list);
        return "/v2/coffee";
    }

    @GetMapping("/coffeeAjax")
    @ResponseBody
    public String doCoffeeAjax(Model model){
        return "/v2/coffeeAjax";
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
        List<VoCoffeeV2> list =  v2Service.doCoffeeList(strStart_date,strEnd_date,strName,strKind);
        log.info(list);

        JSONArray jsonArray = new JSONArray();
        for(VoCoffeeV2 vo : list){
            JSONObject resObj = new JSONObject(vo); //map을 JSON {  } 한 줄 만들어줌
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
        List<VoCoffeeV2> list =  v2Service.doCoffeeList(strStart_date,strEnd_date,strName,strKind);
        model.addAttribute("list",list);
        return "/v2/coffee";
    }

    /* 등록하기 Get */
    @GetMapping("/insert")
    public String doInsert() {
        return "/v2/coffee_Ins";
    }

    /* 등록하기 Post, HttpServletRequest 사용 */
    @PostMapping("/insert")
    public String doInsertPost(@ModelAttribute VoCoffeeV2 voCoffeeV2) {
        log.info("insert, VOcoffee2 : " + voCoffeeV2);
        int intI = v2Service.doInsert(voCoffeeV2);
        return "redirect:/v2/coffee";
    }

    /* 수정하기 Get, @RequestParam 사용 */
    @GetMapping("/update")
    public String doUpdate(@RequestParam(value="coffee_id") String strCoffee_id,Model model){
        Map<String,String> map = v2Service.doListOne(strCoffee_id); //수정 클릭한 해당 row(한 줄) 가져오기
        model.addAttribute("map",map);
        return "/v2/coffee_Up";
    }
    /* 수정하기 POST, @RequestParam 사용 */
    @PostMapping("/update")
    public String doUpdatePost(VoCoffeeV2 voCoffeeV2){
        log.info("수정~ : " + voCoffeeV2);
       int intI = v2Service.doUpdate(voCoffeeV2);
       return "redirect:/v2/coffee";
    }

    /* 1row 삭제하기 , @RequestParam 사용 */
    @GetMapping("/delete")
    public String doDelete(@RequestParam(value="coffee_id") String strCoffee_id){
        int intI = v2Service.doDelete(strCoffee_id);
        return "redirect:/v2/coffee";
    }

    /* 가격을 한번에 수정 */
    @PostMapping("/updatePrice")
    public String doUpdatePrice(@RequestParam(value = "hidden_price") String strPrice,
                                @RequestParam(value = "chkCoffee_id",required = false) List<String> chkList){
        /* @RequestParam으로도, 여러개의 List값을 받을 수 있다,required=false로 이 값이 없이 넘어와도 오류 안나게 */
        log.info("strPrice : " + strPrice);
        log.info("chkList : " + chkList);

        if(chkList != null){
            int int1 = v2Service.doInsertLog(strPrice,chkList);
            int int2 = v2Service.doUpdatePrice(strPrice,chkList);
        }

        /*
        for(String coffee_id : chkList){
            int int1 = v2Service.doInsertLogOld(strPrice,coffee_id);
            int int2 = v2Service.doUpdatePriceOld(strPrice,coffee_id);
        }
         */
        return "redirect:/v2/coffee";
    }
}
