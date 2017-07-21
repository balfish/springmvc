package com.balfish.common.utils.file;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * do not catch this exception, the one who invoke this catch the exception to handle it
 * <p>
 * Created by yhm on 2017/7/12 PM2:17
 */
public class FileUtils {
    /**
     * 读取目录
     *
     * @param directory
     * @return
     * @throws java.io.IOException
     */
    public static List<String> readDirectoryAsList(File directory) throws IOException {
        List<String> resultList = Lists.newArrayList();
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                resultList.addAll(readDirectoryAsList(file));
            } else {
                for (String string : readFileAsList(file)) {
                    resultList.add(string);
                }
            }
        }
        return resultList;
    }

    /**
     * 读取文件
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public static List<String> readFileAsList(File file) throws IOException {
        return Files.readLines(file, Charsets.UTF_8);
    }

    /**
     * 带LineProcessor参数的readLine, 可以根据条件过滤和处理
     */
    public static List<String> readFileInConditionAsList(File file) throws IOException {
        return Files.readLines(file, Charsets.UTF_8, new LineProcessor<List<String>>() {
            List<String> result = Lists.newArrayList();

            public boolean processLine(String line) throws IOException {
                if (!line.contains("#!/bin/bash") && !"".equals(line.trim())) {
                    result.add("with LineProcessor parameter" + line.trim());
                }
                return true;
            }

            public List<String> getResult() {
                return result;
            }

        });
    }

    /**
     * 将指定内容写出到指定路径,可以选择是否以追加的方式(append为true是追加)
     */
    public static void writeFile(File file, String content, Boolean append) throws IOException {
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
            if (!newFile) {
                throw new IOException("文件创建不成功");
            }
        }
        if (append) {
            Files.append(content, file, Charsets.UTF_8);
        } else {
            Files.write(content, file, Charsets.UTF_8);
        }
    }

    /**
     * 初始化结果文件
     */
    public static void initFile(File file) throws IOException {
        if (!file.exists()) {
            return;
        }
        boolean delete = file.delete();
        boolean newFile = file.createNewFile();
        if (!delete || !newFile) {
            throw new IOException("文件写入不成功");
        }
    }
}
