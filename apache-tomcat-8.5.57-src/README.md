## Welcome to Apache Tomcat!

## **源码构建**

教程： https://blog.csdn.net/ganquanzhong/article/details/107267894 

下载地址：[https://tomcat.apache.org/download-80.cgi](https://tomcat.apache.org/download-80.cgi)

![](https://img-blog.csdnimg.cn/20200710210055674.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

### 1.解压源码 apache-tomcat-8.5.57-src

### 2.apache-tomcat-8.5.57-src目录下添加pom文件

`pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
			http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>apache-tomcat-8.5.57-src</artifactId>
    <name>Tomcat8.5</name>
    <version>8.5</version>
    <build>
        <!--指定源目录-->
        <finalName>Tomcat8.5</finalName>
        <sourceDirectory>java</sourceDirectory>
        <resources>
            <resource>
                <directory>java</directory>
            </resource>
        </resources>
        <plugins>
            <!--引入编译插件-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
    <!--tomcat 依赖的基础包-->
    <dependencies>
        <dependency>
            <groupId>org.easymock</groupId>
            <artifactId>easymock</artifactId>
            <version>3.4</version>
        </dependency>
        <dependency>
            <groupId>ant</groupId>
            <artifactId>ant</artifactId>
            <version>1.7.0</version>
        </dependency>
        <dependency>
            <groupId>wsdl4j</groupId>
            <artifactId>wsdl4j</artifactId>
            <version>1.6.2</version>
        </dependency>
        <dependency>
            <groupId>javax.xml</groupId>
            <artifactId>jaxrpc</artifactId>
            <version>1.1</version>
        </dependency>
        <dependency>
            <groupId>org.eclipse.jdt.core.compiler</groupId>
            <artifactId>ecj</artifactId>
            <version>4.5.1</version>
        </dependency>
    </dependencies>
</project>
```

### 3.在apache-tomcat-8.5.57-src 同级目录新建 catalina-home并保证目录下面文件如下
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710210941709.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

**注意：** 上面文件夹apache-tomcat-8.5.57-src里面有的，就复制或者剪切过来，

没有的就新建一个， bin、conf、 webapps 应该是从apache-tomcat-8.5.57-src复制或者剪切过来的

### 4.IDEA引入项目

File->Open 选择解压的 `E:\dev_workspace\tomcat-analysis` (注意！ 选择你自己的路径)

**配置启动**

1. MainClass:

 `org.apache.catalina.startup.Bootstrap`

2. VmOptions: （注意选择自己的目录路径）

`-Dcatalina.home=E:\dev_workspace\tomcat-start\catalina-home`
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710211053761.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

## **启动报错**

### **1. 乱码问题**


主要修改两个位置：

1. org.apache.tomcat.util.res.`StringManager`类  **[注意不要找错类，注意看包名]**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710211115168.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

```java
// 处理乱码问题
try {
     str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
} catch (UnsupportedEncodingException e) {
    e.printStackTrace();
}
```

2. org.apache.jasper.compiler.`Localizer` 类

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710211139259.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

```java
// 处理乱码问题
try {
    errMsg = new String(errMsg.getBytes("ISO-8859-1"), "UTF-8");
} catch (UnsupportedEncodingException e) {
    e.printStackTrace();
}
```



### 2. 无法找到JSP编译类

org.apache.jasper.JasperException: 无法为JSP编译类

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710212855970.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

org.apache.catalina.startup.`ContextConfig`类中添加初始化JSP的解析引擎

```java
// 初始化 jsp 解析引擎
context.addServletContainerInitializer(new JasperInitializer(),null);
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710213122256.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

启动即可：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710213221269.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70)

### 3. 其他报错，不影响运行

严重: 配置应用程序监听器[listeners.ContextListener]错误
java.lang.ClassNotFoundException: listeners.ContextListener

……

严重: 配置应用程序监听器[listeners.SessionListener]错误
java.lang.ClassNotFoundException: listeners.SessionListener

……

严重: 配置应用程序监听器[async.AsyncStockContextListener]错误
java.lang.ClassNotFoundException: async.AsyncStockContextListener

……

严重: 配置应用程序监听器[websocket.drawboard.DrawboardContextListener]错误
java.lang.ClassNotFoundException: websocket.drawboard.DrawboardContextListener

……



运行Tomcat源码，遇到此问题 java.lang.ClassNotFoundException: listeners.ContextListener ，删除webapps下的[examples](http://localhost:8080/examples/)即可



## What Is It?

The Apache Tomcat® software is an open source implementation of the Java
Servlet, JavaServer Pages, Java Expression Language and Java WebSocket
technologies. The Java Servlet, JavaServer Pages, Java Expression Language and
Java WebSocket specifications are developed under the
[Java Community Process](https://jcp.org/en/introduction/overview).

The Apache Tomcat software is developed in an open and participatory
environment and released under the
[Apache License version 2](https://www.apache.org/licenses/). The Apache Tomcat
project is intended to be a collaboration of the best-of-breed developers from
around the world. We invite you to participate in this open development
project. To learn more about getting involved,
[click here](https://tomcat.apache.org/getinvolved.html) or keep reading.

Apache Tomcat software powers numerous large-scale, mission-critical web
applications across a diverse range of industries and organizations. Some of
these users and their stories are listed on the
[PoweredBy wiki page](https://wiki.apache.org/tomcat/PoweredBy).

Apache Tomcat, Tomcat, Apache, the Apache feather, and the Apache Tomcat
project logo are trademarks of the Apache Software Foundation.

### Get It

For every major Tomcat version there is one download page containing
links to the latest binary and source code downloads, but also
links for browsing the download directories and archives:
- [Tomcat 9](https://tomcat.apache.org/download-90.cgi)
- [Tomcat 8](https://tomcat.apache.org/download-80.cgi)
- [Tomcat 7](https://tomcat.apache.org/download-70.cgi)

To facilitate choosing the right major Tomcat version one, we have provided a
[version overview page](https://tomcat.apache.org/whichversion.html).

### Documentation

The documentation available as of the date of this release is
included in the docs webapp which ships with tomcat. You can access that webapp
by starting tomcat and visiting http://localhost:8080/docs/ in your browser.
The most up-to-date documentation for each version can be found at:
- [Tomcat 9](https://tomcat.apache.org/tomcat-9.0-doc/)
- [Tomcat 8](https://tomcat.apache.org/tomcat-8.5-doc/)
- [Tomcat 7](https://tomcat.apache.org/tomcat-7.0-doc/)

### Installation

Please see [RUNNING.txt](RUNNING.txt) for more info.

### Licensing

Please see [LICENSE](LICENSE) for more info.

### Support and Mailing List Information

* Free community support is available through the
[tomcat-users](https://tomcat.apache.org/lists.html#tomcat-users) email list and
a dedicated [IRC channel](https://tomcat.apache.org/irc.html) (#tomcat on
Freenode).

* If you want freely available support for running Apache Tomcat, please see the
resources page [here](https://tomcat.apache.org/findhelp.html).

* If you want to be informed about new code releases, bug fixes,
security fixes, general news and information about Apache Tomcat, please
subscribe to the
[tomcat-announce](https://tomcat.apache.org/lists.html#tomcat-announce) email
list.

* If you have a concrete bug report for Apache Tomcat, please see the
instructions for reporting a bug
[here](https://tomcat.apache.org/bugreport.html).

### Contributing

Please see [CONTRIBUTING](CONTRIBUTING.md) for more info.
