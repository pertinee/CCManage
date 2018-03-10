package com.lcz.manage.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MySQL数据库备份
 *
 * @author GaoHuanjie
 */
public class MySQLBackupUtils {

    /**
     * MySQL数据库导出(注：数据库密码为空的情况会失败)
     * @author luchunzhou
     *
     * @param hostIP       MySQL数据库所在服务器地址IP
     * @param userName     进入数据库所需要的用户名
     * @param password     进入数据库所需要的密码(不为空！)
     * @param savePath     数据库导出文件保存路径（格式：D:/backupDatabase或/usr/local/data）
     * @param fileName     数据库导出文件文件名(格式：people，最终会自动得到people加时间串后缀的格式，如people_20180308131530)
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean backup(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException {


        File saveFile = new File(savePath);
        // 如果目录不存在
        if (!saveFile.exists()) {
            // 创建文件夹
            saveFile.mkdirs();
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName + "_" +getDateString() + ".sql"), "utf8"));
            Process process = Runtime.getRuntime().exec("mysqldump -h " + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
            //0 表示线程正常终止
            if (process.waitFor() == 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 获取时间串（格式：yyyyMMddHHmmss）
     * @return
     */
    private static String getDateString(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static void main(String[] args) {
        try {
            if (MySQLBackupUtils.backup("127.0.0.1", "lcz", "123", "D:/backupDatabase", "test", "test")) {
                System.out.println("数据库成功备份！！！");
            } else {
                System.out.println("数据库备份失败！！！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
} 