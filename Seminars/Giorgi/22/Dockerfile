# Stage 1: Build frontend
FROM node:20-slim AS frontend
WORKDIR /app/src/main/webapp/frontend
RUN npm install -g esbuild
COPY src/main/webapp/frontend/src ./src
RUN esbuild src/index.ts --bundle --outfile=dist/index.js

# Stage 2: Build backend WAR
FROM maven:3.9-eclipse-temurin-21 AS backend
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
COPY --from=frontend /app/src/main/webapp/frontend/dist ./src/main/webapp/frontend/dist
RUN mvn clean package -DskipTests

# Stage 3: Run
FROM tomcat:10.1
COPY --from=backend /app/target/22.war /usr/local/tomcat/webapps/ROOT.war
