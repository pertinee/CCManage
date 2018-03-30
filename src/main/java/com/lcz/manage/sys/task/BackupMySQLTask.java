package com.lcz.manage.sys.task;

import com.lcz.manage.util.MySQLBackupUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * MySQL数据库定时备份
 *
 * @author luchunzhou
 * @date 2018/3/10
 */
@Component("backupMySQLTask")
public class BackupMySQLTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void backup(){
        try {
            if (MySQLBackupUtils.backup("127.0.0.1", "lcz", "123", "D:/backupDatabase", "cc_manage", "cc_manage")) {
                logger.info("【数据库备份成功】");
            } else {
                logger.info("【数据库备份失败】");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
