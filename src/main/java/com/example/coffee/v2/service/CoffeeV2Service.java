package com.example.coffee.v2.service;

import com.example.coffee.v2.dao.CoffeeV2Dao;
import com.example.coffee.v2.vo.VoCoffeeV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CoffeeV2Service {

    @Autowired
    CoffeeV2Dao v2Dao;

    /* 전체 리스트 조회*/
    public List<Map<String, String>> doCoffeeList() {
        List<Map<String,String>> list = v2Dao.doCoffeeList();
        return list;
    }

    /* 전체 리스트 - 오버로딩 함수 사용 */
    public List<VoCoffeeV2> doCoffeeList(String strStart_date, String strEnd_date, String strName, String strKind) {
        List<VoCoffeeV2> list = v2Dao.doCoffeeList(strStart_date,strEnd_date,strName,strKind);
        return list;
    }

    public int doInsert(VoCoffeeV2 voCoffeeV2) {
        int intI = v2Dao.doInsert(voCoffeeV2);
        return intI;
    }

    /* 수정위해, 하나의 row 가져오기*/
    public Map<String, String> doListOne(String strCoffee_id) {
        Map<String,String> map = v2Dao.doListOne(strCoffee_id);
        return map;
    }

    /* 수정하기 POST */
    public int doUpdate(VoCoffeeV2 voCoffeeV2){
        int intI = v2Dao.doUpdate(voCoffeeV2);
        return intI;
    }

    /* 데이터 삭제 */
    public int doDelete(String strCoffee_id){
        int intI = v2Dao.doDelete(strCoffee_id);
        return intI;
    }

    /* 가격 수정 일괄처리 히스토리 로그 입력v1 */
    public int doInsertLogOld(String strPrice, String coffee_id) {
        int int1 = v2Dao.doInsertLogOld(strPrice,coffee_id);
        return int1;
    }
    /* 가격 일괄 수정 처리v1 */
    public int doUpdatePriceOld(String strPrice, String coffee_id) {
        int int2 = v2Dao.doUpdatePriceOld(strPrice,coffee_id);
        return int2;
    }

    /* 가격 수정 일괄처리 히스토리 로그 입력v2 */
    public int doInsertLog(String strPrice, List<String> chkList) {
        int int1 = v2Dao.doInsertLog(strPrice,chkList);
        return int1;
    }
    /* 가격 일괄 수정 처리v2 */
    public int doUpdatePrice(String strPrice, List<String> chkList) {
        int int2 = v2Dao.doUpdatePrice(strPrice,chkList);
        return int2;
    }
}
