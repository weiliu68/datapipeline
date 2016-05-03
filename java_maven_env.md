下载jdk
---
```
wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" \
    http://download.oracle.com/otn-pub/java/jdk/7u71-b14/jdk-7u71-linux-x64.tar.gz
    
tar -xvf jdk-7u71-linux-x64.tar.gz

mv jdk* /opt/jdk
```

下载maven
--- 
```
wget http://apache.arvixe.com/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.tar.gz

tar -xvf apache-maven-3.3.3-bin.tar.gz

mv apache-maven* /opt/maven
```

配置环境变量
---
```
export JAVA_HOME=/opt/jdk
export MAVEN_HOME=/opt/maven
export PATH=$PATH:$JAVA_HOME/bin:$MAVEN_HOME/bin:
```