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
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    /**
     *  API 기능 1번
     * @return List<CustomMap>
     */
    public List<CustomMap> getYearSumAmt(){
        List<CustomMap> sumAmtList = statementRepository.getYearSumAmt();
        return sumAmtList;
    }

    /**
     *  API 기능 2번
     * @return List<CustomMap>
     */
    public List<CustomMap> getYearNoStatement(){
        List<CustomMap> noStatementList = statementRepository.getYearNoStatement();
        return noStatementList;
    }


}
