# CCManage权限系统

- 项目展示：https://www.luchunzhou.club
- 账户：用户名：tom，密码：tom（更多角色用户将在后续开放）

**一、项目介绍**
<br>
CCManage是一个基于springboot的轻量级的权限管理系统，并且在权限功能之外，加上了定时备份数据、定时发送日志邮件等功能，使用简介方便。

**二、采用技术**
- SpringBoot；
- SpringMVC；
- MyBatis；
- Shiro安全框架；
- Druid连接池；
- Quartz作业调度框架；
- Redis缓存方案；
- Freemarker模板引擎；
- AdminLTE（基于 bootstrap 的轻量级后台模板）；
- Swagger2接口文档。

**三、项目模块**
<br>
CCManage系统的具体模块包括：
- 用户管理；
- 角色管理；
- 菜单管理；
- 部门管理；
- 数据字典；
- 文件上传；
- 定时任务；
- 系统日志；
- SQL监控；
- 接口文档；
- 其他模块。

**四、部署方式**
- 通过git下载源码；
- 创建数据库cc_manage，数据库编码为UTF-8；
- 导入doc/cc_manage.sql文件，默认文件中已包含初始化数据；
- 修改application-dev.yml文件，更新MySQL账号和密码；
- 在CCManage目录下，执行mvn clean install；
- 启动CCmanageApplication.java；
- 在浏览器访问登录页面：http://127.0.0.1:8080；
- 输入用户名：admin，密码：123登录系统。

**五、注意事项**
- 超级管理员(sys_user表)的user_id必须是"1"；
- 系统菜单(sys_menu表)的"is_base=1"的数据不可删除，是系统表。

**六、其他**
<br>
如有其他疑问可在下方留言或者访问我的博客进行留言：https://www.luchunzhou.cn

**七、项目展示**
- 登录界面：
![image](doc/imgs/login_page.png)
- 主界面：
![image](doc/imgs/main_page.png)
- 用户管理：
![image](doc/imgs/user.png)
- 角色管理：
![image](doc/imgs/role.png)
- 菜单管理：
![image](doc/imgs/menu.png)
- 部门管理：
![image](doc/imgs/dept.png)
- 数据字典：
![image](doc/imgs/ddic.png)
- 文件上传：
![image](doc/imgs/oss.png)
- 定时任务：
![image](doc/imgs/schedule.png)
- 定时任务（日志列表）：
![image](doc/imgs/schedule_log.png)
- 系统日志：
![image](doc/imgs/logs.png)
- SQL监控：
![image](doc/imgs/druid.png)
- 接口文档：
![image](doc/imgs/swagger2.png)