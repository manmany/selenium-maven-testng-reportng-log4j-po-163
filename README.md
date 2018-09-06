# 环境说明
> 环境： selenium3 + testng + log4j + po + reportng (jdk1.8) 

> 此代码中以photo.163.com网址为例


# 运行
1. IDE工具直接指向testng.xml即可
2. maven执行：mvn test

# 执行报告查看
1. testng.xml 执行可视化报告：${workspace}/test-output/index.html（ testng.xml中需要设置监听器：org.uncommons.reportng.HTMLReporter）
```
    <listeners>
       <listener class-name="org.uncommons.reportng.HTMLReporter"/>
       <listener class-name="org.uncommons.reportng.JUnitXMLReporter"/>
       <listener class-name="Utils.RetryListener"/>
    </listeners><!-- Listener -->
```

2. maven/Jenkins reportNG可视化报告：${workspace}/target/surefire-reports/html/index.html

# pom.xml依赖
## 1. testng

```
    <!-- A testing framework for the JVM  -->
    <dependency>
		<groupId>org.testng</groupId>
		<artifactId>testng</artifactId>
		<version>6.14.2</version>
	</dependency>
```

## 2. reportNG

```
    <!-- ReportNG is a simple HTML reporting plug-in for the TestNG unit-testing framework  -->
    <dependency>
  	    <groupId>org.uncommons</groupId>
	    <artifactId>reportng</artifactId>
	    <version>1.1.4</version>
	     <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.testng</groupId>
                <artifactId>testng</artifactId>
            </exclusion>
        </exclusions>
  	</dependency>  	
  	<!-- [ERROR] java.lang.NoClassDefFoundError: com/google/inject/Injector -->
  	<!-- Google Guice Core Library  -->
 	<dependency>
	    <groupId>com.google.inject</groupId>
	    <artifactId>guice</artifactId>
	    <version>4.1.0</version>
	    <classifier>no_aop</classifier>
	    <scope>test</scope>
	 </dependency>
 	 <dependency>
        <groupId>velocity</groupId>
        <artifactId>velocity-dep</artifactId>
        <version>1.4</version>
     </dependency>
```
 
## 3. Log4j

```
	<dependency>
		 <groupId>log4j</groupId>
		 <artifactId>log4j</artifactId>
		 <version>1.2.16</version>
	</dependency>
```
