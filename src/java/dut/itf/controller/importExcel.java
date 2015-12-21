package dut.itf.controller;

import dut.itf.entity.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class importExcel {

    private Part file;
    private String message;
    private String nameFile;

    public importExcel() {
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String save() {
        try (InputStream input = file.getInputStream()) {
            Files.copy(input, new File("D:\\SCHOOL\\Session7\\Project\\DEMO\\USE\\khoacongnghethongtin\\khoacongnghethongtin\\web\\files", file.getSubmittedFileName()).toPath());
            nameFile = file.getSubmittedFileName();
            return "importData";
        } catch (IOException e) {
            e.printStackTrace();
            return "importData";
        }
    }

    //delete old image
    public void deleteFileMember() {
        File currentfile = new File("D:\\SCHOOL\\Session7\\Project\\DEMO\\USE\\khoacongnghethongtin\\khoacongnghethongtin\\web\\files\\" + nameFile);
        currentfile.delete();
    }

    public String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    public Date toDate(String date) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.parse(date);
    }

//    public ArrayList<Student> updateProcess() throws IOException, BiffException, ParseException {
//        File f = new File("D:\\SCHOOL\\Session7\\Project\\DEMO\\USE\\khoacongnghethongtin\\khoacongnghethongtin\\web\\files\\" + "student.xls");
//        Workbook wb = Workbook.getWorkbook(f);
//        Sheet sheet = wb.getSheet(1);
//        int rows = sheet.getRows();
//        int cols = sheet.getColumns();
//        ArrayList<Student> list = new ArrayList<>();
//        for (int row = 1; row < rows; row++) {
//            Student l = new Student();
//            for (int col = 0; col < cols; col++) {
//                if (!sheet.getCell(col, row).getContents().isEmpty()) {
//                    if (col == 0) {
//                        l.setIdstudent(Integer.parseInt(sheet.getCell(col, row).getContents()));
//                    }
//                    if (col == 1) {
//                        l.setPassword(sheet.getCell(col, row).getContents());
//                    }
//                    if (col == 2) {
//                        l.setName(sheet.getCell(col, row).getContents());
//                    }
//                    if (col == 3) {
//                        l.setDob(toDate(sheet.getCell(col, row).getContents()));
//                    }
//                    if (col == 4) {
//                        l.setClasses(sheet.getCell(col, row).getContents());
//                    }
//                    if (col == 5) {
//                        l.setAddress(sheet.getCell(col, row).getContents());
//                    }
//                    if (col == 6) {
//                        l.setPhone(sheet.getCell(col, row).getContents());
//                    }
//                    if (col == 7) {
//                        l.setEmail(sheet.getCell(col, row).getContents());
//                    }
//                }
//            }
//            list.add(l);
//        }
//        return list;
//    }
//
//    public void readExcel() {
//        try {
//            FileInputStream fileN = new FileInputStream(new File("D:\\SCHOOL\\Session7\\Project\\DEMO\\USE\\khoacongnghethongtin\\khoacongnghethongtin\\web\\files\\student.xls"));
//
//            //Create Workbook instance holding reference to .xlsx file
//            XSSFWorkbook workbook = new XSSFWorkbook(fileN);
//
//            //Get first/desired sheet from the workbook
//            XSSFSheet sheet = workbook.getSheetAt(0);
//
//            //Iterate through each rows one by one
//            Iterator<Row> rowIterator = sheet.iterator();
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                //For each row, iterate through all the columns
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    //Check the cell type and format accordingly
//                    switch (cell.getCellType()) {
//                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                    }
//                }
//                System.out.println("");
//            }
//            fileN.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void main(String[] args) throws IOException, BiffException, ParseException {
//        importExcel bean = new importExcel();
//        bean.readExcel();
//    }

}
