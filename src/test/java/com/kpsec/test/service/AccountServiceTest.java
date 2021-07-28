package com.kpsec.test.service;

import com.kpsec.test.model.AccountResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void getAccountByBranchCode()  {
        List<AccountResult> accountByBranchCodeList = accountService.getAccountByBranchCode("A");
        assertThat(accountByBranchCodeList,hasSize(3));
    }
}
