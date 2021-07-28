package com.kpsec.test.repository;

import com.kpsec.test.model.entity.Statement;
import com.kpsec.test.util.CustomMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, String> {

    @Query(value =  "SELECT   CAST(b.year as INT) as year , b.account_name AS name, b.account_no AS acct_no, b.sum_amt AS sum_amt " +
                    "         FROM   ( SELECT  a.*, row_number() over(partition BY a.year ORDER BY a.sum_amt DESC) AS rowid " +
                    "FROM     ( SELECT  SUM(price-charge) AS sum_amt, ac.account_no, LEFT(sm.statement_date,4) AS YEAR , ac.account_name "+
                    "FROM     statement sm, account ac "+
                    "WHERE    1=1"+
                    "AND      sm.account_no = ac.account_no "+
                    "AND      LEFT(sm.statement_date,4) IN ('2018', '2019') "+
                    "AND      sm.cancel_at = 'N' "+
                    "GROUP BY LEFT(sm.statement_date,4), ac.account_no, ac.account_name "+
                    ") AS a ) AS b "+
                    "WHERE  b.rowid = 1 "
                    , nativeQuery = true)
    List<CustomMap> getYearSumAmt();

    @Query(value =  "SELECT CASE yeargroup WHEN '2018' THEN 2019 " +
                    "       WHEN '2019' THEN 2018 END  AS year, " +
                    "       account_name  AS name, ex.account_no AS acct_no " +
                    "FROM   ( SELECT  account_no, group_concat(YEAR ) AS yearGroup " +
                    "       FROM     ( SELECT  ACCOUNT_NO, LEFT(statement_date,4) AS YEAR " +
                    "                FROM     STATEMENT " +
                    "                WHERE    1         =1 " +
                    "                AND      cancel_at ='N' " +
                    "                GROUP BY ACCOUNT_NO, LEFT(statement_date,4) " +
                    "                ) " +
                    "                AS a " +
                    "       GROUP BY account_no " +
                    "       ) AS ex, account ac " +
                    "WHERE  yearGroup    != '2018,2019' " +
                    "AND    yearGroup    != 2020 " +
                    "AND    ex.account_no = ac.account_no"
                    , nativeQuery = true)
    List<CustomMap> getYearNoStatement();

}