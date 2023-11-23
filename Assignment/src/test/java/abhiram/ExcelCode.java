package abhiram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCode {

	static File f;
	public static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static FileOutputStream fout;
	static String path = "./target/patient.xlsx";

	public void CreateHeaders() {

		f = new File(path);


		workbook = new XSSFWorkbook();

		sheet = workbook.createSheet("Information");

		row = sheet.createRow(0);

		row.createCell(0).setCellValue("Hospital Name");
		row.createCell(1).setCellValue("Patient count");
		try {
			fout = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void createsheet(List<Tabledata> td) {
		System.out.println(td);
		CreateHeaders();

		int i = 0;
		for (Tabledata dt : td) {

			row = sheet.createRow(++i);
			System.out.println("in for loop " + dt);
			row.createCell(0).setCellValue(dt.getHospitalName());
			row.createCell(1).setCellValue(dt.getPatientnumber());


		}
		System.out.println(sheet.getPhysicalNumberOfRows() + "rows count");
		try {
			workbook.write(fout);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
