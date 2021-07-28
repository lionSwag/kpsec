package com.kpsec.test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementCompositeKey implements Serializable {

    private int statementDate;

    private String statementNo;

}
