**CCManage权限系统**

**一、介绍**
<br>
本项目是参考[renren-security-boot](https://gitee.com/babaio/renren-security-boot)进行改造创建的项目，再此特别感谢[人人开源](http://www.renren.io/open/)。
<br>
在此基础上进行改进和完善，包括：
- 前端使用FreeMarker模板引擎；
- 替换vue.js为更基础易用的jQuery；
- 基于AdminLTE重写后台管理页面；
- 修改数据库表的id为uuid，重写sql代码、java代码；
- 精简配置文件；
- 添加Swagger2接口文档；
- 其他改进。

**二、采用技术**
- SpringBoot；
- SpringMVC；
- MyBatis；
- Shiro；
- Druid；
- Quartz；
- Redis；
- Freemarker；
- AdminLTE；
- Swagger2。

**三、功能**
<br>
项目具有用户管理、角色管理、菜单管理、部门管理、数据字典、文件上传、定时任务、系统日志、SQL监控、接口文档等功能，是一个基础的权限管理系统。

**四、部署方式**
- 通过git下载源码；
- 创建数据库cc_manage，数据库编码为UTF-8；
- 导入doc/cc_manage.sql文件，默认文件中已包含初始化数据；
- 修改application-dev.yml文件，更新MySQL账号和密码；
- 在CCManage目录下，执行mvn clean install；
- 启动CCmanageApplication.java;
- 在浏览器输入http://127.0.0.1:8080访问登录页面；
- 输入用户名：admin,密码：123登录系统。

**五、注意事项**
- 超级管理员(sys_user表)的user_id必须是"1"；
- 系统菜单(sys_menu表)的"is_base=1"的数据不可删除，是系统表。

**六、其他**
<br>
如有其他疑问可在下方留言或者访问[我的博客](https://www.luchunzhou.cn)进行留言。

**七、项目展示**
![image](http://p20dkvms1.bkt.clouddn.com/d1.png)
![image](http://p20dkvms1.bkt.clouddn.com/d2.png)
![image](http://p20dkvms1.bkt.clouddn.com/d3.png)
![image](http://p20dkvms1.bkt.clouddn.com/d4.png)
![image](http://p20dkvms1.bkt.clouddn.com/d5.png)
![image](http://p20dkvms1.bkt.clouddn.com/d6.png)
![image](http://p20dkvms1.bkt.clouddn.com/d7.png)