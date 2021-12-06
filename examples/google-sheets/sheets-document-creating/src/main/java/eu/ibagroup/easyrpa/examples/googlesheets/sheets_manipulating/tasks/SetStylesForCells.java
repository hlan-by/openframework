package eu.ibagroup.easyrpa.examples.googlesheets.sheets_manipulating.tasks;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.TextFormat;
import com.google.api.services.sheets.v4.model.TextRotation;
import eu.ibagroup.easyrpa.engine.annotation.ApTaskEntry;
import eu.ibagroup.easyrpa.engine.annotation.Configuration;
import eu.ibagroup.easyrpa.engine.apflow.ApTask;
import eu.ibagroup.easyrpa.openframework.googlesheets.Document;
import eu.ibagroup.easyrpa.openframework.googlesheets.GSheetCellStyle;
import eu.ibagroup.easyrpa.openframework.googlesheets.GoogleSheets;
import eu.ibagroup.easyrpa.openframework.googlesheets.spreadsheet.Sheet;
import eu.ibagroup.easyrpa.openframework.googlesheets.style.GSheetColors;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.IOException;

@ApTaskEntry(name = "Set Styles for Cells")
@Slf4j
public class SetStylesForCells extends ApTask {
    @Configuration(value = "spreadsheet.id")
    private String spreadsheetId;

    @Inject
    GoogleSheets service;


    @Override
    public void execute() throws IOException {

        String cell1Ref = "E27";
        String cell2Ref = "C4";
        String cell3Ref = "C5";

        log.info("Set cell styles for spreadsheet document located at '{}'", spreadsheetId);

        Document doc = new Document(service, spreadsheetId);

        Sheet activeSheet = doc.getActiveSheet();

        log.info("Change style for cell '{}' of  sheet '{}'", cell1Ref, activeSheet.getName());

        CellData cellData = doc.getCellData(cell1Ref);

        CellFormat cellFormat = cellData.getUserEnteredFormat() != null ? cellData.getUserEnteredFormat() : cellData.getEffectiveFormat();

        GSheetCellStyle style = new GSheetCellStyle(cellFormat);

        style.setBackgroundColor(GSheetColors.MAGENTA.get().toNativeColor()).setTextRotation(new TextRotation().setAngle(90));

        cellFormat.setBackgroundColor(GSheetColors.MAGENTA.get().toNativeColor()).setTextRotation(new TextRotation().setAngle(90));

        TextFormat textFormat = style.getTextFormat();
        textFormat.setBold(true).setFontSize(14).setStrikethrough(true)
                .setForegroundColor(GSheetColors.CYAN.get().toNativeColor());

        cellFormat.setTextFormat(textFormat);
        cellData.setUserEnteredFormat(cellFormat);


        doc.setCellData(cell1Ref, cellData);

//
//        ExcelCellStyle boldRedStyle = cell.getStyle().bold(true).color(ExcelColors.RED.get());
//
//        ExcelCellStyle newStyle = new ExcelCellStyle().fontSize(14)
//                .fill(FillPatternType.SOLID_FOREGROUND).background(ExcelColors.LIGHT_GREEN.get());
//
//        activeSheet.getCell(cell3Ref).setStyle(newStyle);


        log.info("Style for cell '{}' has been specified successfully.", cell1Ref);
    }
}
