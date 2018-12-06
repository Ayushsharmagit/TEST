package org.project.testengine.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.project.testengine.question.dto.QuestionDTO;

public class ExcelReader {
	public static ArrayList<QuestionDTO> readExcel(String path) throws IOException {
		
		File file =new File(path);
		FileInputStream fs= new FileInputStream(file);
		HSSFWorkbook workBook =new HSSFWorkbook(fs);//get the workBook from xls file
		HSSFSheet sheet =workBook.getSheetAt(0);//get first sheet from the workBook & 
		                                                        //pointer at zero
		boolean isFirstRow=false;
		Iterator<Row> rows=sheet.rowIterator();
		ArrayList<QuestionDTO>questions=new ArrayList<>();
		while(rows.hasNext()) {//return true if having next elements
			Row row=rows.next();//retrun the next elements 
			if(!isFirstRow) {
				isFirstRow=true;
				continue;   //it go back to'Row'statement
			}
			
			Iterator<Cell>cells=row.cellIterator();
			
			QuestionDTO questionDTO=new QuestionDTO();
			String Option[]=new String[4];
			int index=0;
			int columnIndex=1;
			
			while(cells.hasNext()) {
				Cell cell=cells.next();
				
				if(cell.getCellType()==CellType.STRING) {
					if(columnIndex==2) {
						questionDTO.setName((String)cell.getStringCellValue());
					}
					
					else 
						if(columnIndex>=3&&columnIndex<7) {
							Option[index]=cell.getStringCellValue();
							index++;
						}
						else
							if(columnIndex==7) {
								questionDTO.setOption(Option);
								questionDTO.setRightAnswer((String)cell.getStringCellValue());
							}
				}
				
				else
					if(cell.getCellType()==CellType.NUMERIC && columnIndex==1||columnIndex==8) {
						if(columnIndex==1) {
							questionDTO.setId((int) cell.getNumericCellValue());
						}
						else {
							questionDTO.setScore((int) cell.getNumericCellValue());
					}
				}
				columnIndex++;
			}
			questions.add(questionDTO);
		}
		workBook.close();
		fs.close();
		return questions;
		
	}
	public static void main(String[] args) throws IOException {
		String path="E:\\Other\\Code\\Eclips SimRel\\TestEngine\\book.xls";
		ArrayList<QuestionDTO> questions=ExcelReader.readExcel(path);
		System.out.println("Question:::::"+questions);
		System.out.println("Question:::::"+questions.toString());
	}
}
