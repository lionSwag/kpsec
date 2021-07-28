package com.kpsec.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kpsec.test.model.AccountResult;
import com.kpsec.test.model.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT account_no as accountNo, account_name as accountName, branch_code as branchCode FROM account WHERE branch_code = :branchCode", nativeQuery = true)
    List<AccountResult> getAccountByBranchCode(@Param("branchCode") String branchCode);

}
