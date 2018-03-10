package com.lcz.manage.mysql;


import com.lcz.manage.util.MySQLBackupUtils;
import org.junit.Test;

/**
 * Created by luchunzhou on 2018/3/9.
 */
public class MysqlTest {

    @Test
    public void backupMySQL() {
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
