package eu.ibagroup.easyrpa.examples.database.working_with_table_records.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

import java.util.Date;

@Data
@DatabaseTable(tableName = "invoices")
public class Invoice {

    public static final String DB_DATE_FORMAT = "yyyy-MM-dd";

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    @DatabaseField(columnName = "invoice_number", canBeNull = false)
    private int invoiceNumber;

    @DatabaseField(columnName = "invoice_date", dataType = DataType.DATE, format = DB_DATE_FORMAT)
    public Date invoiceDate;

    @DatabaseField(columnName = "customer_name", canBeNull = false)
    private String customerName;

    @DatabaseField(canBeNull = false)
    private double amount;

    @DatabaseField(canBeNull = false)
    private boolean outdated;

    public Invoice() {
    }

    public Invoice(int invoiceNumber, Date invoiceDate, String customerName, double amount) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.amount = amount;
    }
}