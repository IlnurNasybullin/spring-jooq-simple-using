package org.example.service;

import org.example.jooq.public_.tables.Insertsum;
import org.example.jooq.public_.tables.pojos.SumTable;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InsertingService {

    private final DSLContext dslContext;

    public InsertingService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @PostConstruct
    public void insertSum() {
        SumTable sumTable = dslContext.selectFrom(Insertsum.INSERTSUM.call(3L, 4L))
                .fetchOneInto(SumTable.class);

        System.out.printf("ADDER1 is: %d%n", sumTable.getAdder1());
        System.out.printf("ADDER2 is: %d%n", sumTable.getAdder2());
        System.out.printf("SUM is %d%n", sumTable.getSumm());
    }
}
