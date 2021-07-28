package com.kpsec.test.service;

import com.kpsec.test.model.AccountResult;
import com.kpsec.test.repository.AccountRepository;
import com.kpsec.test.repository.BranchRepository;
import com.kpsec.test.repository.StatementRepository;
import com.kpsec.test.util.CustomMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    /**
     * API 기능 3번
     * 연도별로 리스트를 뽑기 위해 DB에서 연도만 추출
     * @return
     */
    public List<Integer> getStatementYear(){
        List<Integer> statementYearList = branchRepository.getStatementYear();
        return statementYearList;
    }

    /**
     * API 기능 3번
     * 관리점별 거래금액을 추출
     * @return
     */
    public List<CustomMap> getBranchYearSumAmt(){
        List<CustomMap> branchYearSumAmtList = branchRepository.getBranchYearSumAmt();
        return branchYearSumAmtList;
    }

    /**
     * API 기능 4번
     * @param branchName
     * @return
     */
    public CustomMap getBranchSumAmt(String branchName){
        CustomMap branchSumAmt = branchRepository.getBranchSumAmt(branchName);
        return branchSumAmt;
    }

}
