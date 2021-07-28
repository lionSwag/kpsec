package com.kpsec.test.infra;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.kpsec.test.model.entity.Branch;
import com.kpsec.test.model.entity.Statement;
import com.kpsec.test.repository.BranchRepository;
import com.kpsec.test.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.kpsec.test.model.entity.Account;
import com.kpsec.test.repository.AccountRepository;

@Component
public class InitData {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    StatementRepository statementRepository;

    @PostConstruct
    private void initAccount() throws IOException {
        if (accountRepository.count() == 0) {
            Resource resource = new ClassPathResource("계좌정보.csv");
            List<Account> accountList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Account.builder().accountNo(split[0]).accountName(split[1]).branchCode(split[2])
                                .build();
                    }).collect(Collectors.toList());
            accountRepository.saveAll(accountList);
        }
    }

    @PostConstruct
    private void initBranch() throws IOException {
        if (branchRepository.count() == 0) {
            Resource resource = new ClassPathResource("관리점정보.csv");
            List<Branch> branchList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Branch.builder().branchCode(split[0]).branchName(split[1]).build();
                    }).collect(Collectors.toList());
            branchRepository.saveAll(branchList);
        }
    }

    @PostConstruct
    private void initStatement() throws IOException {
        if (statementRepository.count() == 0) {
            Resource resource = new ClassPathResource("거래내역.csv");
            List<Statement> statementList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Statement.builder().statementDate(Integer.parseInt(split[0])).accountNo(split[1]).statementNo(split[2])
                                .price(Integer.parseInt(split[3])).charge(Integer.parseInt(split[4])).cancelAt(split[5])
                                .build();
                    }).collect(Collectors.toList());
            statementRepository.saveAll(statementList);
        }
    }

}
