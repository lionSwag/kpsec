package com.kpsec.test.service;

import com.kpsec.test.util.CustomMap;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.not;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class StatementServiceTest {

    @Autowired
    private StatementService statementService;

    @Test
    public void getYearSumAmt()  {
        List<CustomMap> yearSumAmtList = statementService.getYearSumAmt();
        assertThat(yearSumAmtList,hasSize(2));
        assertThat(yearSumAmtList, not(IsEmptyCollection.empty()));
    }

    @Test
    public void getYearNoStatement(){
        List<CustomMap> yearNoStatement = statementService.getYearNoStatement();
        assertThat(yearNoStatement,hasSize(2));
        assertThat(yearNoStatement, not(IsEmptyCollection.empty()));
    }

}
