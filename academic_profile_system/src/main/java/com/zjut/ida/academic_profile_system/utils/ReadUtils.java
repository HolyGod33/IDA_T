package com.zjut.ida.academic_profile_system.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.zjut.ida.academic_profile_system.entity.CoAuthor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author kokoryh on 2022/6/1
 */
public class ReadUtils {

    /**
     * 读取json文件，返回json串
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream getInputStream(InputStream in) throws IOException {
        PushbackInputStream testin = new PushbackInputStream(in);
        int ch = testin.read();
        if (ch != 0xEF) {
            testin.unread(ch);
        } else if ((ch = testin.read()) != 0xBB) {
            testin.unread(ch);
            testin.unread(0xef);
        } else if ((ch = testin.read()) != 0xBF) {
            throw new IOException("错误的UTF-8格式文件");
        }
        return testin;
    }

    public static <T> List<T> readCSVFileToBean(String filePath, Class<T> clazz) throws IOException {
        String srcPath = ReadUtils.class.getClassLoader().getResource(filePath).getPath();
        File csvFile = new File(srcPath);
        FileInputStream fileInputStream = new FileInputStream(csvFile);
        MultipartFile multipartFile = new MockMultipartFile(csvFile.getName(), fileInputStream);

        InputStreamReader in = null;
        CsvToBean<T> csvToBean = null;
        try {
            InputStream inputStream = getInputStream(multipartFile.getInputStream());
            in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);
            csvToBean = new CsvToBeanBuilder<T>(in).withMappingStrategy(strategy).build();
        } catch (Exception e) {
//            logger.error("数据转化失败");
            return null;
        }
        return csvToBean.parse();
    }

    public static List<String[]> readCSVFileToList(String filePath) throws IOException {
        String srcPath = ReadUtils.class.getClassLoader().getResource(filePath).getPath();
        File csvFile = new File(srcPath);
        FileInputStream fileInputStream = new FileInputStream(csvFile);
        MultipartFile multipartFile = new MockMultipartFile(csvFile.getName(), fileInputStream);

        List<String[]> list = new ArrayList<String[]>();
        int i = 0;
        try {
            CSVReader csvReader = new CSVReaderBuilder(
                    new BufferedReader(
                            new InputStreamReader(multipartFile.getInputStream(), "utf-8"))).build();
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()) {
                String[] next = iterator.next();
                System.out.println(next);
                //去除第一行的表头，从第二行开始
                if (i >= 1) {
                    list.add(next);
                }
                i++;
            }
            return list;
        } catch (Exception e) {
            System.out.println("CSV文件读取异常");
            return list;
        }
    }

    public static void main(String[] args) throws IOException {
        List<CoAuthor> coAuthorList = ReadUtils.readCSVFileToBean("aps/Co_Author.csv", CoAuthor.class);
        try {
            FSearchTool tool = new FSearchTool(coAuthorList, "source", "target");
            System.out.println(tool.searchTasks("黄亮"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        List<SimilarScholar> similarScholarList = ReadUtils.readCSVFileToBean("aps/articleSimilar.csv", SimilarScholar.class);
//        List<ScholarRelation> scholarRelations = ReadUtils.readCSVFileToBean("aps/relations.csv", ScholarRelation.class);
//        System.out.println(scholarRelations);

    }

}
