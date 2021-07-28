package com.kpsec.test.contoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kpsec.test.repository.BranchRepository;
import com.kpsec.test.repository.StatementRepository;
import com.kpsec.test.util.CustomMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kpsec.test.model.AccountResult;
import com.kpsec.test.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Sample")
@RestController
@RequestMapping("/test/")
public class AccountController {

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public class PageNotFoundException extends NullPointerException { }

    @Autowired
    private AccountService accountService;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private StatementRepository statementRepository;

    @ApiOperation(value = "sample")
    @GetMapping(value = "/acount")
    public List<AccountResult> getAccountInfo(String branchCode) {
        return accountService.getAccountByBranchCode(branchCode);
    }

    @ApiOperation(value = "연도별 합계 금액 추출", notes = "2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출하는 API 개발")
    @GetMapping(value = "/sumAmt")
    public List<CustomMap> getYearSumAmt() {
        return statementRepository.getYearSumAmt();
    }

    @ApiOperation(value = "거래가 없는 고객을 추출", notes = "2018년 또는 2019년에 거래가 없는 고객을 추출하는 API 개발")
    @GetMapping(value = "/noStatement")
    public List<CustomMap> getYearNoStatement() {
        return statementRepository.getYearNoStatement();
    }

    @ApiOperation(value = "연도별 관리점별 거래금액 합계 추출 ", notes = "연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력하는 API 개발")
    @GetMapping(value = "/branchYearSumAmt")
    public List<Map<String,Object>> getStatementYear() {
        List<Integer> list = branchRepository.getStatementYear();
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        List<CustomMap> list2 = branchRepository.getBranchYearSumAmt();
        for (Integer year: list) {
            Map<String,Object> resultMap = new HashMap<String,Object>();
            List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
            for(CustomMap map : list2){
                if(year.equals(Integer.valueOf(map.get("year").toString()))){
                    dataList.add(map);
                }
            }
            resultMap.put("year", year);
            resultMap.put("dataList", dataList);
            resultList.add(resultMap);
        }
        return resultList;
    }

    @ApiOperation(value = "지점명 입력 시 거래금액 합계 출력", notes ="분당점과 판교점을 통폐합하여 판교점으로 관리점 이관을 하였습니다. 지점명을 입력하면 해당지점의 거래금액 합계를 출력하는 API 개발")
    @GetMapping(value = "/branchSumAmt")
    public CustomMap getBranchSumAmt(String brName)  {
        CustomMap map = branchRepository.getBranchSumAmt(brName);
        if(null == map){
            throw new  PageNotFoundException();
        }
        return map;
    }

}
