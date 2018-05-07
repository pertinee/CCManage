package com.lcz.manage.sys.task;

import com.lcz.manage.util.MySQLBackupUtils;
import com.lcz.manage.util.exception.CCException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * MySQL数据库定时备份
 *
 * @author luchunzhou
 * @date 2018/3/10
 */
@Component("backupMySQLTask")
@Slf4j
public class BackupMySQLTask {

    @Value("${backup.hostIP}")
    private String hostIP;
    @Value("${backup.userName}")
    private String userName;
    @Value("${backup.password}")
    private String password;
    @Value("${backup.savePath}")
    private String savePath;
    @Value("${backup.fileName}")
    private String fileName;
    @Value("${backup.databaseName}")
    private String databaseName;

    public void backup() {
        try {
            if (MySQLBackupUtils.backup(hostIP, userName, password, savePath, fileName, databaseName)) {
                log.info("数据库备份成功");
            } else {
                log.info("数据库备份失败");
                throw new CCException("数据库备份失败");
            }
        } catch (InterruptedException e) {
            log.error("数据库备份错误");
            throw new CCException("数据库备份错误");
        }
    }

}
