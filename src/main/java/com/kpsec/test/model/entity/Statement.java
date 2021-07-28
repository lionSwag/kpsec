package com.kpsec.test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(StatementCompositeKey.class)
public class Statement {
    @Id
    @Column(name="statement_date")
    private int statementDate; // 거래일자

    private String accountNo; // 계좌번호

    @Id
    @Column(name="statement_no")
    private String statementNo; // 거래번호

    private int price; // 금액

    private int charge; // 수수료

    private String cancelAt; // 취소여부

}
