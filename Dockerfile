FROM hub.c.163.com/dwyane/openjdk:8

ADD target/*.jar app.jar

# 设置时区与语言环境变量
ENV TIME_ZONE=Asia/Shanghai \
        LANG=en_US.UTF-8 \
        LANGUAGE=en_US.UTF-8 \
        LC_ALL=en_US.UTF-8

# 执行以下命令设置时区
RUN echo "${TIME_ZONE}" > /etc/timezone \
  && ln -sf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime

# 指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT exec java ${JAVA_OPTS} -jar /app.jar