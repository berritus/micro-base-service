package mis.berritus.cloud.app.common.component.impl;

import mis.berritus.cloud.app.bean.excel.ExportDataBean;
import mis.berritus.cloud.app.common.component.ExcelService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Component
public class ExcelServiceImpl implements ExcelService {
    @Override
    public void exportList(String sheetName, List<String> header, Map<String, List<String>> dataMap,
                           OutputStream outputStream)
    throws InvocationTargetException, IllegalAccessException{
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetName);

        if(dataMap == null || dataMap.size() == 0){
            return;
        }

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        for(int i = 0; i < header.size(); i++){
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header.get(i));
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }

        //遍历结果集，把内容加入表格
        int i = 1;
        for(Map.Entry<String, List<String>> enrty : dataMap.entrySet()){
            HSSFRow row1 = sheet.createRow(i);
            List<String> list = enrty.getValue();
            for(int j = 0; j < list.size(); j++){
                HSSFCell cell = row1.createCell(j);
                HSSFRichTextString text = new HSSFRichTextString(list.get(j));
                cell.setCellValue(text);
            }

            i++;
        }

        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportListForSheet(List<ExportDataBean> listData, OutputStream outputStream)
            throws InvocationTargetException, IllegalAccessException {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        if (CollectionUtils.isEmpty(listData)) {
            return;
        }

        for (ExportDataBean exportDataBean : listData) {
            //生成一个表格
            HSSFSheet sheet = workbook.createSheet(exportDataBean.getSheetName());

            List<String> header = exportDataBean.getHeaders();
            Map<String, List<String>> dataMap = exportDataBean.getDataMap();

            if(dataMap == null || dataMap.size() == 0){
                //return;
            }

            //设置表格列宽度为10个字节
            sheet.setDefaultColumnWidth(10);

            //创建第一行表头
            HSSFRow headrow = sheet.createRow(0);
            for(int i = 0; i < header.size(); i++){
                //创建一个单元格
                HSSFCell cell = headrow.createCell(i);
                //创建一个内容对象
                HSSFRichTextString text = new HSSFRichTextString(header.get(i));
                //将内容对象的文字内容写入到单元格中
                cell.setCellValue(text);
            }

            //遍历结果集，把内容加入表格
            int i = 1;
            for(Map.Entry<String, List<String>> enrty : dataMap.entrySet()){
                HSSFRow row1 = sheet.createRow(i);
                List<String> list = enrty.getValue();
                for(int j = 0; j < list.size(); j++){
                    HSSFCell cell = row1.createCell(j);
                    HSSFRichTextString text = new HSSFRichTextString(list.get(j));
                    cell.setCellValue(text);
                }

                i++;
            }
        }

        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
