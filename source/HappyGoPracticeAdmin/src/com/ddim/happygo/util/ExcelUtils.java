package com.ddim.happygo.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry; 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile; 
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
/**
 * Excel匯入匯出工具類
 *
 * @author sunnyzyq
 * @date 2021/12/17
 */
public class ExcelUtils {
 
    private static final String XLSX = ".xlsx";
    private static final String XLS = ".xls";
    public static final String ROW_MERGE = "row_merge";
    public static final String COLUMN_MERGE = "column_merge";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String ROW_NUM = "rowNum";
    private static final String ROW_DATA = "rowData";
    private static final String ROW_TIPS = "rowTips";
    private static final int CELL_OTHER = 0;
    private static final int CELL_ROW_MERGE = 1;
    private static final int CELL_COLUMN_MERGE = 2;
    private static final int IMG_HEIGHT = 30;
    private static final int IMG_WIDTH = 30;
    private static final char LEAN_LINE = '/';
    private static final int BYTES_DEFAULT_LENGTH = 10240;
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();
 
 
    public static <T> List<T> readFile(File file, Class<T> clazz) throws Exception {
        JSONArray array = readFile(file);
        return getBeanList(array, clazz);
    }
 
    public static <T> List<T> readMultipartFile(MultipartFile mFile, Class<T> clazz) throws Exception {
        JSONArray array = readMultipartFile(mFile);
        return getBeanList(array, clazz);
    }
 
    public static JSONArray readFile(File file) throws Exception {
        return readExcel(null, file);
    }
 
    public static JSONArray readMultipartFile(MultipartFile mFile) throws Exception {
        return readExcel(mFile, null);
    }
 
    private static <T> List<T> getBeanList(JSONArray array, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        Map<Integer, String> uniqueMap = new HashMap<>(16);
        for (int i = 0; i < array.size(); i++) {
            list.add(getBean(clazz, array.getJSONObject(i), uniqueMap));
        }
        return list;
    }
 
    /**
     * 獲取每個物件的資料
     */
    private static <T> T getBean(Class<T> c, JSONObject obj, Map<Integer, String> uniqueMap) throws Exception {
        T t = c.newInstance();
        Field[] fields = c.getDeclaredFields();
        List<String> errMsgList = new ArrayList<>();
        boolean hasRowTipsField = false;
        StringBuilder uniqueBuilder = new StringBuilder();
        int rowNum = 0;
        for (Field field : fields) {
            // 行號
            if (field.getName().equals(ROW_NUM)) {
                rowNum = obj.getInteger(ROW_NUM);
                field.setAccessible(true);
                field.set(t, rowNum);
                continue;
            }
            // 是否需要設定異常資訊
            if (field.getName().equals(ROW_TIPS)) {
                hasRowTipsField = true;
                continue;
            }
            // 原始資料
            if (field.getName().equals(ROW_DATA)) {
                field.setAccessible(true);
                field.set(t, obj.toString());
                continue;
            }
            // 設定對應屬性值
            setFieldValue(t,field, obj, uniqueBuilder, errMsgList);
        }
        // 資料唯一性校驗
        if (uniqueBuilder.length() > 0) {
            if (uniqueMap.containsValue(uniqueBuilder.toString())) {
                Set<Integer> rowNumKeys = uniqueMap.keySet();
                for (Integer num : rowNumKeys) {
                    if (uniqueMap.get(num).equals(uniqueBuilder.toString())) {
                        errMsgList.add(String.format("資料唯一性校驗失敗,(%s)與第%s行重複)", uniqueBuilder, num));
                    }
                }
            } else {
                uniqueMap.put(rowNum, uniqueBuilder.toString());
            }
        }
        // 失敗處理
        if (errMsgList.isEmpty() && !hasRowTipsField) {
            return t;
        }
        StringBuilder sb = new StringBuilder();
        int size = errMsgList.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                sb.append(errMsgList.get(i));
            } else {
                sb.append(errMsgList.get(i)).append(";");
            }
        }
        // 設定錯誤資訊
        for (Field field : fields) {
            if (field.getName().equals(ROW_TIPS)) {
                field.setAccessible(true);
                field.set(t, sb.toString());
            }
        }
        return t;
    }
 
    private static <T> void setFieldValue(T t, Field field, JSONObject obj, StringBuilder uniqueBuilder, List<String> errMsgList) {
        // 獲取 ExcelImport 註解屬性
        ExcelImport annotation = field.getAnnotation(ExcelImport.class);
        if (annotation == null) {
            return;
        }
        String cname = annotation.value();
        if (cname.trim().length() == 0) {
            return;
        }
        // 獲取具體值
        String val = null;
        if (obj.containsKey(cname)) {
            val = getString(obj.getString(cname));
        }
        if (val == null) {
            return;
        }
        field.setAccessible(true);
        // 判斷是否必填
        boolean require = annotation.required();
        if (require && val.isEmpty()) {
            errMsgList.add(String.format("[%s]不能為空", cname));
            return;
        }
        // 資料唯一性獲取
        boolean unique = annotation.unique();
        if (unique) {
            if (uniqueBuilder.length() > 0) {
                uniqueBuilder.append("--").append(val);
            } else {
                uniqueBuilder.append(val);
            }
        }
        // 判斷是否超過最大長度
        int maxLength = annotation.maxLength();
        if (maxLength > 0 && val.length() > maxLength) {
            errMsgList.add(String.format("[%s]長度不能超過%s個字元(當前%s個字元)", cname, maxLength, val.length()));
        }
        // 判斷當前屬性是否有對映關係
        LinkedHashMap<String, String> kvMap = getKvMap(annotation.kv());
        if (!kvMap.isEmpty()) {
            boolean isMatch = false;
            for (String key : kvMap.keySet()) {
                if (kvMap.get(key).equals(val)) {
                    val = key;
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) {
                errMsgList.add(String.format("[%s]的值不正確(當前值為%s)", cname, val));
                return;
            }
        }
        // 其餘情況根據型別賦值
        String fieldClassName = field.getType().getSimpleName();
        try {
            if ("String".equalsIgnoreCase(fieldClassName)) {
                field.set(t, val);
            } else if ("boolean".equalsIgnoreCase(fieldClassName)) {
                field.set(t, Boolean.valueOf(val));
            } else if ("int".equalsIgnoreCase(fieldClassName) || "Integer".equals(fieldClassName)) {
                try {
                    field.set(t, Integer.valueOf(val));
                } catch (NumberFormatException e) {
                    errMsgList.add(String.format("[%s]的值格式不正確(當前值為%s)", cname, val));
                }
            } else if ("double".equalsIgnoreCase(fieldClassName)) {
                field.set(t, Double.valueOf(val));
            } else if ("long".equalsIgnoreCase(fieldClassName)) {
                field.set(t, Long.valueOf(val));
            } else if ("BigDecimal".equalsIgnoreCase(fieldClassName)) {
                field.set(t, new BigDecimal(val));
            } else if("Date".equalsIgnoreCase(fieldClassName) && !val.isEmpty() && !val.equals("")) {
            	
            	DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
            	Date date = fmt.parse(val);
            	field.set(t, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static JSONArray readExcel(MultipartFile mFile, File file) throws IOException {
        boolean fileNotExist = (file == null || !file.exists());
        if (mFile == null && fileNotExist) {
            return new JSONArray();
        }
        // 解析表格資料
        InputStream in;
        String fileName;
        if (mFile != null) {
            // 上傳檔案解析
            in = mFile.getInputStream();
            fileName = getString(mFile.getOriginalFilename()).toLowerCase();
        } else {
            // 本地檔案解析
            in = new FileInputStream(file);
            fileName = file.getName().toLowerCase();
        }
        Workbook book;
        if (fileName.endsWith(XLSX)) {
            book = new XSSFWorkbook(in);
        } else if (fileName.endsWith(XLS)) {
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(in);
            book = new HSSFWorkbook(poifsFileSystem);
        } else {
            return new JSONArray();
        }
        JSONArray array = read(book);
//        book.close();
        in.close();
        return array;
    }
 
    private static JSONArray read(Workbook book) {
        // 獲取 Excel 檔案第一個 Sheet 頁面
        Sheet sheet = book.getSheetAt(0);
        return readSheet(sheet);
    }
 
    private static JSONArray readSheet(Sheet sheet) {
        // 首行下標
        int rowStart = sheet.getFirstRowNum();
        // 尾行下標
        int rowEnd = sheet.getLastRowNum();
        // 獲取表頭行
        Row headRow = sheet.getRow(rowStart);
        if (headRow == null) {
            return new JSONArray();
        }
        int cellStart = headRow.getFirstCellNum();
        int cellEnd = headRow.getLastCellNum();
        Map<Integer, String> keyMap = new HashMap<>();
        for (int j = cellStart; j < cellEnd; j++) {
            // 獲取表頭資料
            String val = getCellValue(headRow.getCell(j));
            if (val != null && val.trim().length() != 0) {
                keyMap.put(j, val);
            }
        }
        // 如果表頭沒有資料則不進行解析
        if (keyMap.isEmpty()) {
            return (JSONArray) Collections.emptyList();
        }
        // 獲取每行JSON物件的值
        JSONArray array = new JSONArray();
        // 如果首行與尾行相同，表明只有一行，返回表頭資料
        if (rowStart == rowEnd) {
            JSONObject obj = new JSONObject();
            // 新增行號
            obj.put(ROW_NUM, 1);
            for (int i : keyMap.keySet()) {
                obj.put(keyMap.get(i), "");
            }
            array.add(obj);
            return array;
        }
        for (int i = rowStart + 1; i <= rowEnd; i++) {
            Row eachRow = sheet.getRow(i);
            JSONObject obj = new JSONObject();
            // 新增行號
            obj.put(ROW_NUM, i + 1);
            StringBuilder sb = new StringBuilder();
            for (int k = cellStart; k < cellEnd; k++) {
                if (eachRow != null) {
                    String val = getCellValue(eachRow.getCell(k));
                    // 所有資料新增到裡面，用於判斷該行是否為空
                    sb.append(val);
                    obj.put(keyMap.get(k), val);
                }
            }
            if (sb.length() > 0) {
                array.add(obj);
            }
        }
        return array;
    }
 
    private static String getCellValue(Cell cell) {
        // 空白或空
        if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            return "";
        }
        // String型別
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            String val = cell.getStringCellValue();
            if (val == null || val.trim().length() == 0) {
                return "";
            }
            return val.trim();
        }
        // 數位型別
        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            // 科學計數法型別
            return NUMBER_FORMAT.format(cell.getNumericCellValue()) + "";
        }
        // 布林值型別
        if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue() + "";
        }
        // 錯誤型別
        return cell.getCellFormula();
    }
 
    public static <T> void exportTemplate(HttpServletResponse response, String fileName, Class<T> clazz) {
        exportTemplate(response, fileName, fileName, clazz, false);
    }
 
    public static <T> void exportTemplate(HttpServletResponse response, String fileName, String sheetName,
                                          Class<T> clazz) {
        exportTemplate(response, fileName, sheetName, clazz, false);
    }
 
    public static <T> void exportTemplate(HttpServletResponse response, String fileName, Class<T> clazz,
                                          boolean isContainExample) {
        exportTemplate(response, fileName, fileName, clazz, isContainExample);
    }
 
    public static <T> void exportTemplate(HttpServletResponse response, String fileName, String sheetName,
                                          Class<T> clazz, boolean isContainExample) {
        // 獲取表頭欄位
        List<ExcelClassField> headFieldList = getExcelClassFieldList(clazz);
        // 獲取表頭資料和範例資料
        List<List<Object>> sheetDataList = new ArrayList<>();
        List<Object> headList = new ArrayList<>();
        List<Object> exampleList = new ArrayList<>();
        Map<Integer, List<String>> selectMap = new LinkedHashMap<>();
        for (int i = 0; i < headFieldList.size(); i++) {
            ExcelClassField each = headFieldList.get(i);
            headList.add(each.getName());
            exampleList.add(each.getExample());
            LinkedHashMap<String, String> kvMap = each.getKvMap();
            if (kvMap != null && kvMap.size() > 0) {
                selectMap.put(i, new ArrayList<>(kvMap.values()));
            }
        }
        sheetDataList.add(headList);
        if (isContainExample) {
            sheetDataList.add(exampleList);
        }
        // 匯出資料
        export(response, fileName, sheetName, sheetDataList, selectMap);
    }
 
    private static <T> List<ExcelClassField> getExcelClassFieldList(Class<T> clazz) {
        // 解析所有欄位
        Field[] fields = clazz.getDeclaredFields();
        boolean hasExportAnnotation = false;
        Map<Integer, List<ExcelClassField>> map = new LinkedHashMap<>();
        List<Integer> sortList = new ArrayList<>();
        for (Field field : fields) {
            ExcelClassField cf = getExcelClassField(field);
            if (cf.getHasAnnotation() == 1) {
                hasExportAnnotation = true;
            }
            int sort = cf.getSort();
            if (map.containsKey(sort)) {
                map.get(sort).add(cf);
            } else {
                List<ExcelClassField> list = new ArrayList<>();
                list.add(cf);
                sortList.add(sort);
                map.put(sort, list);
            }
        }
        Collections.sort(sortList);
        // 獲取表頭
        List<ExcelClassField> headFieldList = new ArrayList<>();
        if (hasExportAnnotation) {
            for (Integer sort : sortList) {
                for (ExcelClassField cf : map.get(sort)) {
                    if (cf.getHasAnnotation() == 1) {
                        headFieldList.add(cf);
                    }
                }
            }
        } else {
            headFieldList.addAll(map.get(0));
        }
        return headFieldList;
    }
 
    private static ExcelClassField getExcelClassField(Field field) {
        ExcelClassField cf = new ExcelClassField();
        String fieldName = field.getName();
        cf.setFieldName(fieldName);
        ExcelExport annotation = field.getAnnotation(ExcelExport.class);
        // 無 ExcelExport 註解情況
        if (annotation == null) {
            cf.setHasAnnotation(0);
            cf.setName(fieldName);
            cf.setSort(0);
            return cf;
        }
        // 有 ExcelExport 註解情況
        cf.setHasAnnotation(1);
        cf.setName(annotation.value());
        String example = getString(annotation.example());
        if (!example.isEmpty()) {
            if (isNumeric(example)) {
                cf.setExample(Double.valueOf(example));
            } else {
                cf.setExample(example);
            }
        } else {
            cf.setExample("");
        }
        cf.setSort(annotation.sort());
        // 解析對映
        String kv = getString(annotation.kv());
        cf.setKvMap(getKvMap(kv));
        return cf;
    }
 
    private static LinkedHashMap<String, String> getKvMap(String kv) {
        LinkedHashMap<String, String> kvMap = new LinkedHashMap<>();
        if (kv.isEmpty()) {
            return kvMap;
        }
        String[] kvs = kv.split(";");
        if (kvs.length == 0) {
            return kvMap;
        }
        for (String each : kvs) {
            String[] eachKv = getString(each).split("-");
            if (eachKv.length != 2) {
                continue;
            }
            String k = eachKv[0];
            String v = eachKv[1];
            if (k.isEmpty() || v.isEmpty()) {
                continue;
            }
            kvMap.put(k, v);
        }
        return kvMap;
    }
 
    /**
     * 匯出表格到本地
     *
     * @param file      本地檔案物件
     * @param sheetData 匯出資料
     */
    public static void exportFile(File file, List<List<Object>> sheetData) {
        if (file == null) {
            System.out.println("檔案建立失敗");
            return;
        }
        if (sheetData == null) {
            sheetData = new ArrayList<>();
        }
        export(null, file, file.getName(), file.getName(), sheetData, null);
    }
 
    /**
     * 匯出表格到本地
     *
     * @param <T>      匯出資料類似，和K型別保持一致
     * @param filePath 檔案父路徑（如：D:/doc/excel/）
     * @param fileName 檔名稱（不帶尾綴，如：學生表）
     * @param list     匯出資料
     * @throws IOException IO異常
     */
    public static <T> File exportFile(String filePath, String fileName, List<T> list) throws IOException {
        File file = getFile(filePath, fileName);
        List<List<Object>> sheetData = getSheetData(list);
        exportFile(file, sheetData);
        return file;
    }
 
    /**
     * 獲取檔案
     *
     * @param filePath filePath 檔案父路徑（如：D:/doc/excel/）
     * @param fileName 檔名稱（不帶尾綴，如：使用者表）
     * @return 本地File檔案物件
     */
    private static File getFile(String filePath, String fileName) throws IOException {
        String dirPath = getString(filePath);
        String fileFullPath;
        if (dirPath.isEmpty()) {
            fileFullPath = fileName;
        } else {
            // 判定資料夾是否存在，如果不存在，則級聯建立
            File dirFile = new File(dirPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            // 獲取資料夾全名
            if (dirPath.endsWith(String.valueOf(LEAN_LINE))) {
                fileFullPath = dirPath + fileName + XLSX;
            } else {
                fileFullPath = dirPath + LEAN_LINE + fileName + XLSX;
            }
        }
        System.out.println(fileFullPath);
        File file = new File(fileFullPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
 
    private static <T> List<List<Object>> getSheetData(List<T> list) {
        // 獲取表頭欄位
        List<ExcelClassField> excelClassFieldList = getExcelClassFieldList(list.get(0).getClass());
        List<String> headFieldList = new ArrayList<>();
        List<Object> headList = new ArrayList<>();
        Map<String, ExcelClassField> headFieldMap = new HashMap<>();
        for (ExcelClassField each : excelClassFieldList) {
            String fieldName = each.getFieldName();
            headFieldList.add(fieldName);
            headFieldMap.put(fieldName, each);
            headList.add(each.getName());
        }
        // 新增表頭名稱
        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(headList);
        // 獲取表資料
        for (T t : list) {
            Map<String, Object> fieldDataMap = getFieldDataMap(t);
            Set<String> fieldDataKeys = fieldDataMap.keySet();
            List<Object> rowList = new ArrayList<>();
            for (String headField : headFieldList) {
                if (!fieldDataKeys.contains(headField)) {
                    continue;
                }
                Object data = fieldDataMap.get(headField);
                if (data == null) {
                    rowList.add("");
                    continue;
                }
                ExcelClassField cf = headFieldMap.get(headField);
                // 判斷是否有對映關係
                LinkedHashMap<String, String> kvMap = cf.getKvMap();
                if (kvMap == null || kvMap.isEmpty()) {
                    rowList.add(data);
                    continue;
                }
                String val = kvMap.get(data.toString());
                if (isNumeric(val)) {
                    rowList.add(Double.valueOf(val));
                } else {
                    rowList.add(val);
                }
            }
            sheetDataList.add(rowList);
        }
        return sheetDataList;
    }
 
    private static <T> Map<String, Object> getFieldDataMap(T t) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                Object object = field.get(t);
                map.put(fieldName, object);
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
 
    public static void exportEmpty(HttpServletResponse response, String fileName) {
        List<List<Object>> sheetDataList = new ArrayList<>();
        List<Object> headList = new ArrayList<>();
        headList.add("匯出無資料");
        sheetDataList.add(headList);
        export(response, fileName, sheetDataList);
    }
 
    public static void export(HttpServletResponse response, String fileName, List<List<Object>> sheetDataList) {
        export(response, fileName, fileName, sheetDataList, null);
    }
 
    public static void export(HttpServletResponse response, String fileName, String sheetName,
                              List<List<Object>> sheetDataList) {
        export(response, fileName, sheetName, sheetDataList, null);
    }
 
    public static void export(HttpServletResponse response, String fileName, String sheetName,
                              List<List<Object>> sheetDataList, Map<Integer, List<String>> selectMap) {
        export(response, null, fileName, sheetName, sheetDataList, selectMap);
    }
 
    public static <T, K> void export(HttpServletResponse response, String fileName, List<T> list, Class<K> template) {
        // list 是否為空
        boolean lisIsEmpty = list == null || list.isEmpty();
        // 如果模板資料為空，且匯入的資料為空，則匯出空檔案
        if (template == null && lisIsEmpty) {
            exportEmpty(response, fileName);
            return;
        }
        // 如果 list 資料，則匯出模板資料
        if (lisIsEmpty) {
            exportTemplate(response, fileName, template);
            return;
        }
        // 匯出資料
        List<List<Object>> sheetDataList = getSheetData(list);
        export(response, fileName, sheetDataList);
    }
 
    public static void export(HttpServletResponse response, String fileName, List<List<Object>> sheetDataList, Map<Integer, List<String>> selectMap) {
        export(response, fileName, fileName, sheetDataList, selectMap);
    }
 
    private static void export(HttpServletResponse response, File file, String fileName, String sheetName,
                               List<List<Object>> sheetDataList, Map<Integer, List<String>> selectMap) {
        // 整個 Excel 表格 book 物件
        SXSSFWorkbook book = new SXSSFWorkbook();
        // 每個 Sheet 頁
        Sheet sheet = book.createSheet(sheetName);
        Drawing patriarch = sheet.createDrawingPatriarch();
        // 設定表頭背景色（灰色）
        CellStyle headStyle = book.createCellStyle();
        headStyle.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.index);
        headStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        // 設定表身背景色（預設色）
        CellStyle rowStyle = book.createCellStyle();
        rowStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        rowStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 設定表格列寬度（預設為15個位元組）
        sheet.setDefaultColumnWidth(15);
        // 建立合併演演算法陣列
        int rowLength = sheetDataList.size();
        int columnLength = sheetDataList.get(0).size();
        int[][] mergeArray = new int[rowLength][columnLength];
        for (int i = 0; i < sheetDataList.size(); i++) {
            // 每個 Sheet 頁中的行資料
            Row row = sheet.createRow(i);
            List<Object> rowList = sheetDataList.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                // 每個行資料中的單元格資料
                Object o = rowList.get(j);
                int v = 0;
//                if (o instanceof URL) {
//                    // 如果要匯出圖片的話, 連結需要傳遞 URL 物件
//                    setCellPicture(book, row, patriarch, i, j, (URL) o);
//                } else {
                    Cell cell = row.createCell(j);
                    if (i == 0) {
                        // 第一行為表頭行，採用灰色底背景
                        v = setCellValue(cell, o, headStyle);
                    } else {
                        // 其他行為資料行，預設白底色
                        v = setCellValue(cell, o, rowStyle);
                    }
//                }
                mergeArray[i][j] = v;
            }
        }
        // 合併單元格
        mergeCells(sheet, mergeArray);
        // 設定下拉選單
        setSelect(sheet, selectMap);
        // 寫資料
        if (response != null) {
            // 前端匯出
            try {
                write(response, book, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 本地匯出
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file);
                ByteArrayOutputStream ops = new ByteArrayOutputStream();
                book.write(ops);
                fos.write(ops.toByteArray());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 合併當前Sheet頁的單元格
     *
     * @param sheet      當前 sheet 頁
     * @param mergeArray 合併單元格演演算法
     */
    private static void mergeCells(Sheet sheet, int[][] mergeArray) {
        // 橫向合併
        for (int x = 0; x < mergeArray.length; x++) {
            int[] arr = mergeArray[x];
            boolean merge = false;
            int y1 = 0;
            int y2 = 0;
            for (int y = 0; y < arr.length; y++) {
                int value = arr[y];
                if (value == CELL_COLUMN_MERGE) {
                    if (!merge) {
                        y1 = y;
                    }
                    y2 = y;
                    merge = true;
                } else {
                    merge = false;
                    if (y1 > 0) {
                        sheet.addMergedRegion(new CellRangeAddress(x, x, (y1 - 1), y2));
                    }
                    y1 = 0;
                    y2 = 0;
                }
            }
            if (y1 > 0) {
                sheet.addMergedRegion(new CellRangeAddress(x, x, (y1 - 1), y2));
            }
        }
        // 縱向合併
        int xLen = mergeArray.length;
        int yLen = mergeArray[0].length;
        for (int y = 0; y < yLen; y++) {
            boolean merge = false;
            int x1 = 0;
            int x2 = 0;
            for (int x = 0; x < xLen; x++) {
                int value = mergeArray[x][y];
                if (value == CELL_ROW_MERGE) {
                    if (!merge) {
                        x1 = x;
                    }
                    x2 = x;
                    merge = true;
                } else {
                    merge = false;
                    if (x1 > 0) {
                        sheet.addMergedRegion(new CellRangeAddress((x1 - 1), x2, y, y));
                    }
                    x1 = 0;
                    x2 = 0;
                }
            }
            if (x1 > 0) {
                sheet.addMergedRegion(new CellRangeAddress((x1 - 1), x2, y, y));
            }
        }
    }
 
    private static void write(HttpServletResponse response, SXSSFWorkbook book, String fileName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String name = new String(fileName.getBytes("GBK"), "ISO8859_1") + XLSX;
        response.addHeader("Content-Disposition", "attachment;filename=" + name);
        ServletOutputStream out = response.getOutputStream();
        book.write(out);
        out.flush();
        out.close();
    }
 
    private static int setCellValue(Cell cell, Object o, CellStyle style) {
        // 設定樣式
        cell.setCellStyle(style);
        // 資料為空時
        if (o == null || o.toString().isEmpty()) {
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("");
            return CELL_OTHER;
        }
        // 是否為字串
        if (o instanceof String) {
            String s = o.toString();
            if (isNumeric(s)) {
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(s));
                return CELL_OTHER;
            } else {
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(s);
            }
            if (s.equals(ROW_MERGE)) {
                return CELL_ROW_MERGE;
            } else if (s.equals(COLUMN_MERGE)) {
                return CELL_COLUMN_MERGE;
            } else {
                return CELL_OTHER;
            }
        }
        // 是否為字串
        if (o instanceof Integer || o instanceof Long || o instanceof Double || o instanceof Float) {
            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(Double.parseDouble(o.toString()));
            return CELL_OTHER;
        }
        // 是否為Boolean
        if (o instanceof Boolean) {
            cell.setCellType(XSSFCell.CELL_TYPE_BOOLEAN);
            cell.setCellValue((Boolean) o);
            return CELL_OTHER;
        }
        // 如果是BigDecimal，則預設3位小數
        if (o instanceof BigDecimal) {
            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(((BigDecimal) o).setScale(3, RoundingMode.HALF_UP).doubleValue());
            return CELL_OTHER;
        }
        // 如果是Date資料，則顯示格式化資料
        if (o instanceof Date) {
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(formatDate((Date) o));
            return CELL_OTHER;
        }
        // 如果是其他，則預設字串型別
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(o.toString());
        return CELL_OTHER;
    }
 
//    private static void setCellPicture(SXSSFWorkbook wb, Row sr, Drawing<?> patriarch, int x, int y, URL url) {
//        // 設定圖片寬高
//        sr.setHeight((short) (IMG_WIDTH * IMG_HEIGHT));
//        // （jdk1.7版本try中定義流可自動關閉）
//        try (InputStream is = url.openStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            byte[] buff = new byte[BYTES_DEFAULT_LENGTH];
//            int rc;
//            while ((rc = is.read(buff, 0, BYTES_DEFAULT_LENGTH)) > 0) {
//                outputStream.write(buff, 0, rc);
//            }
//            // 設定圖片位置
//            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, y, x, y + 1, x + 1);
//            // 設定這個，圖片會自動填滿單元格的長寬
//            anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);
//            patriarch.createPicture(anchor, wb.addPicture(outputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
 
    private static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }
 
    private static void setSelect(Sheet sheet, Map<Integer, List<String>> selectMap) {
        if (selectMap == null || selectMap.isEmpty()) {
            return;
        }
        Set<Entry<Integer, List<String>>> entrySet = selectMap.entrySet();
        for (Entry<Integer, List<String>> entry : entrySet) {
            int y = entry.getKey();
            List<String> list = entry.getValue();
            if (list == null || list.isEmpty()) {
                continue;
            }
            String[] arr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }
            DataValidationHelper helper = sheet.getDataValidationHelper();
            CellRangeAddressList addressList = new CellRangeAddressList(1, 65000, y, y);
            DataValidationConstraint dvc = helper.createExplicitListConstraint(arr);
            DataValidation dv = helper.createValidation(dvc, addressList);
            if (dv instanceof HSSFDataValidation) {
                dv.setSuppressDropDownArrow(false);
            } else {
                dv.setSuppressDropDownArrow(true);
                dv.setShowErrorBox(true);
            }
            sheet.addValidationData(dv);
        }
    }
 
    private static boolean isNumeric(String str) {
        if ("0.0".equals(str)) {
            return true;
        }
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
 
    private static String getString(String s) {
        if (s == null) {
            return "";
        }
        if (s.isEmpty()) {
            return s;
        }
        return s.trim();
    }
 
}
