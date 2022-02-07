FROM openjdk:11
ENV TZ=Asia/Baku
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
COPY build/libs/*.*ar app.jar
EXPOSE 2042
ARG DEPLOY_ENV
ENV SPRING_PROFILE=${DEPLOY_ENV}
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILE}", "/app.jar"]