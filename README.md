# 环境说明
> 环境： selenium3 + testng + log4j + po + reportng (jdk1.8) 
>
> 此代码中以photo.163.com网址为例


# 运行
1. IDE工具直接指向testng.xml即可
2. maven执行：mvn test

# 执行报告查看
1. testng.xml 执行可视化报告：${workspace}/test-output/index.html（ testng.xml中需要设置监听器：org.uncommons.reportng.HTMLReporter）
2. maven/Jenkins reportNG可视化报告：${workspace}/target/surefire-reports/html/index.html


