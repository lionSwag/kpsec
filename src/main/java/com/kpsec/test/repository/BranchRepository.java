package com.kpsec.test.repository;

import com.kpsec.test.model.entity.Branch;
import com.kpsec.test.util.CustomMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {

    @Query(value = "SELECT LEFT(statement_date,4) as year FROM STATEMENT GROUP BY LEFT(statement_date,4)", nativeQuery = true)
    List<Integer> getStatementYear();

    @Query(value =  "SELECT   SUM(price) as sum_amt, br.branch_code as br_code, br.branch_name as br_name, CAST(LEFT(statement_date,4) AS INT) AS YEAR " +
                    "FROM     STATEMENT sm, branch br, account ac " +
                    "WHERE    1=1 " +
                    "AND      sm.cancel_at  = 'N' " +
                    "AND      sm.account_no  = ac.account_no " +
                    "AND      br.branch_code = ac.branch_code " +
                    "GROUP BY br.branch_code, br.branch_name, CAST(LEFT(statement_date,4) AS INT) " +
                    "ORDER BY CAST(LEFT(statement_date,4) AS INT), SUM(price) DESC ", nativeQuery = true)
    List<CustomMap> getBranchYearSumAmt();

    @Query(value =  "SELECT  br_name, br_code, SUM(price) AS sum_amt " +
                    "FROM    ( SELECT price, CASE br.branch_code WHEN 'B' THEN 'A' ELSE br.branch_code END AS br_code, " +
                    "                 CASE br.branch_name WHEN '분당점' THEN '판교점' ELSE br.branch_name END AS br_name  " +
                    "          FROM   STATEMENT sm, branch br, account ac " +
                    "          WHERE  1=1 " +
                    "          AND    sm.cancel_at  = 'N' " +
                    "          AND    sm.account_no  = ac.account_no " +
                    "          AND    br.branch_code = ac.branch_code " +
                    "         ) AS ex  " +
                    "where br_name = :brName  " +
                    "GROUP BY br_code, br_name", nativeQuery = true)
    CustomMap getBranchSumAmt(@Param("brName") String brName);


}
