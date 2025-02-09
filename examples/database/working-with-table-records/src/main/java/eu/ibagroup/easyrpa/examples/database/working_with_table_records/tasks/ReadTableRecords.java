package eu.ibagroup.easyrpa.examples.database.working_with_table_records.tasks;

import eu.ibagroup.easyrpa.engine.annotation.ApTaskEntry;
import eu.ibagroup.easyrpa.engine.apflow.ApTask;
import eu.ibagroup.easyrpa.examples.database.working_with_table_records.entity.Invoice;
import eu.ibagroup.easyrpa.openframework.database.DatabaseService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;

@ApTaskEntry(name = "Read Table Records")
@Slf4j
public class ReadTableRecords extends ApTask {

    @Inject
    private DatabaseService dbService;

    @Override
    public void execute() {
        log.info("Reading existing records in the table that is used to store entity '{}'", Invoice.class.getName());
        List<Invoice> invoices = dbService.withConnection("testdb", (c) -> {
            return c.selectAll(Invoice.class);
        });

        log.info("Fetched records:");
        for (Invoice invoice : invoices) {
            log.info("{}", invoice);
        }
    }
}
