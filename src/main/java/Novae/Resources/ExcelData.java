package Novae.Resources;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	public ArrayList<String> getExcelData(String palabra) throws IOException {

		String src = System.getProperty("user.dir");
		String path = src + "//excelsource//exceldata.xlsx";
		ArrayList<String> a = new ArrayList<>();

		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		System.out.println(sheets);

		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("Data 1")) {
						column = value.getColumnIndex();
					}
				}
				System.out.println(column);
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(palabra)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext())
						{
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING)
							{
								a.add(c.getStringCellValue());
							} else if (c.getCellType() == CellType.NUMERIC)
							{
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}

							else {
								System.out.println(c.getNumericCellValue());
							}
						}
					}
				}
			}
		}
		fis.close();
		workbook.close();
		return a;
	}
}
