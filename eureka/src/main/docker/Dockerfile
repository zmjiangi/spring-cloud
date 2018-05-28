FROM frolvlad/alpine-oraclejdk8:slim

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD eureka-1.0.jar app.jar

RUN sh -c 'touch /app.jar'

EXPOSE 9081

ENV JAVA_OPTS="$JAVA_OPTS -Duser.timezone=Asia/Shanghai -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]