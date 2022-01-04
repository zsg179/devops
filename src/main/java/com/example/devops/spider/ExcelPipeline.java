package com.example.devops.spider;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: sg zhu
 * @create: 2022-01-04 16:50
 */
public class ExcelPipeline extends FilePersistentBase implements Pipeline {

    private String filename;//文件名
    private int rows = 0;//当前要编辑的行
    private HSSFWorkbook workbook;//工作蒲
    private HSSFSheet sheet;//工作表

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void process(ResultItems resultItems, Task task) {
//提取保存的内容
        List<String> hrefs = resultItems.get("href");
        List<String> texts = resultItems.get("text");

        logger.debug(hrefs.toString());
        logger.debug(texts.toString());

        for (int i = 0; i < hrefs.size(); i++) {
            //创建工作表的行
            HSSFRow row = sheet.createRow(rows);
            row.createCell(0).setCellValue(rows);
            row.createCell(2).setCellValue(hrefs.get(i));
            row.createCell(1).setCellValue(texts.get(i));
            rows++;
        }

        //写完之后保存
        save();
    }

    public ExcelPipeline(String path) {

        //设置保存路径
        setPath(path);

        filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xls";
        //创建工作薄对象
        workbook = new HSSFWorkbook();
        //创建工作表对象
        sheet = workbook.createSheet("爬取结果");

        //创建工作表的行
        HSSFRow row = sheet.createRow(rows);

        //创建标题
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("名称");
        row.createCell(2).setCellValue("连接地址");

        //每写完一行我们就要开始写下一行
        rows++;
    }

    /**
     * 保存表格
     **/
    private synchronized void save() {
        try {
            //文档输出
            FileOutputStream out = new FileOutputStream(getFile(this.path + filename));
            workbook.write(out);
            out.close();
            logger.info(this.path + filename + "存储完毕");
        } catch (IOException e) {
            logger.warn("存储失败", e);
        }
    }
}
