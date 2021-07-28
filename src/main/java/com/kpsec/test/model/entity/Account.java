package com.kpsec.test.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    private String accountNo; // 계좌번호

    private String accountName; // 계좌명

    private String branchCode; //관리점코드

}
