package com.kpsec.test.service;

import com.kpsec.test.util.CustomMap;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class BranchServiceTest {

    @Autowired
    private BranchService branchService;

    @Test
    public void getStatementYearTest(){
        List<Integer> testYearList = branchService.getStatementYear();
        assertThat(testYearList, contains(2018, 2019, 2020));
    }

    @Test
    public void getBranchYearSumAmt(){
        List<CustomMap> testBranchYearSumAmtList = branchService.getBranchYearSumAmt();
        assertThat(testBranchYearSumAmtList,hasSize(9));
    }

    @Test
    public void  getBranchSumAmt(){

        CustomMap testBranchSumAmtMap = branchService.getBranchSumAmt("판교점");
        assertThat(testBranchSumAmtMap, IsMapContaining.hasEntry("brCode","A"));
        assertThat(testBranchSumAmtMap, IsMapContaining.hasKey("sumAmt"));
        assertThat(testBranchSumAmtMap, IsMapContaining.hasValue("판교점"));
        assertThat(testBranchSumAmtMap.size(), is(3));

    }
}
