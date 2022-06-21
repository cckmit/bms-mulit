/*
 * Copyright (c) 2017~2020 zb-z All rights reserved.
 *
 * zb-z 2020/1/3 15:20
 *
 * 版权所有，侵权必究！
 */
package cn.amigosoft.common.utils;

import cn.amigosoft.common.exception.RenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.UUID;

/**
 * <p>
 * 资源文件下载
 * </p>
 *
 * @author : zb-z
 * @version : 1.0
 * @date : 2020/1/3 15:20
 */
@Slf4j
public class ResourceDownLoadUtils {

    /**
     * 下载excel文件
     *
     * @param response
     * @param url      文件路径
     * @param newName  下载后的文件名字
     */
    public static void downloadExcel(HttpServletResponse response, String url, String newName) {
        File excelFile;
        try {
            Resource resource = new ClassPathResource(url);
            excelFile = resource.getFile();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            String name = URLEncoder.encode(newName, "UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(newName, "UTF-8") + ".xls");
            try (
                    ServletOutputStream out = response.getOutputStream();
                 FileInputStream input = new FileInputStream(excelFile)) {
                // 判断输入或输出是否准备好
                if (out != null) {
                    int temp;
                    while ((temp = input.read()) != -1) {
                        out.write(temp);
                    }
                }
            } catch (IOException e) {
                log.error("文件下载异常:", e);
                throw new RenException("文件下载异常。");
            }
        } catch (IOException e) {
            log.error("文件下载异常:", e);
            throw new RenException("文件下载异常。");
        }
    }

    /**
     * 选件打包多个远程图片下载Zip文件
     * @param response
     * @param urls
     * @param name
     * @param newName
     */
    public static void downloadZipForPatrolPolicy(HttpServletResponse response, ArrayList urls, List name, String newName) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(newName, "UTF-8") + ".zip");

            String[] files = new String[urls.size()];
            urls.toArray(files);

            try ( ZipOutputStream out = new ZipOutputStream(response.getOutputStream())){
                for (int i = 0; i < files.length; i++) {
                    URL url = new URL(files[i]);
                    out.putNextEntry(new ZipEntry(name.get(i)+".jpg"));
                    InputStream fis = url.openConnection().getInputStream();
                    byte[] buffer = new byte[1024];
                    int r = 0;
                    while ((r = fis.read(buffer)) != -1) {
                        out.write(buffer, 0, r);
                    }
                    fis.close();
                }
                out.flush();
                out.close();
            } catch (IOException e) {
                log.error("文件下载异常:", e);
                throw new RenException("文件下载异常。");
            }
        } catch (IOException e) {
            log.error("文件下载异常:", e);
            throw new RenException("文件下载异常。");
        }
    }

    public static void downloadZip(HttpServletResponse response, List<String> urlList, String downloadFilename) {
        ZipOutputStream zos = null;
        BufferedInputStream br = null;
        try {
            //文件的名称
            response.reset();
            //设置格式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + downloadFilename);
            zos = new ZipOutputStream(response.getOutputStream());
            for (String url : urlList) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        zos.putNextEntry(new ZipEntry(UUID.randomUUID() + url.substring(url.lastIndexOf("."))));
                        br = new BufferedInputStream(inputStream);
                        byte[] buffer = new byte[1024];
                        int r = 0;
                        while ((r = br.read(buffer)) != -1) {
                            zos.write(buffer, 0, r);
                        }
                    }
                } catch (IOException e) {
                    log.info("获取网络图片出现异常，图片路径为：" + url);
                    e.printStackTrace();
                }
            }
            zos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
