# Stage 1: Build frontend
FROM node:20-slim AS frontend
ENV NPM_CONFIG_REGISTRY=https://registry.npmjs.org/
WORKDIR /app/src/main/webapp/frontend
COPY src/main/webapp/frontend/package*.json ./
RUN npm ci
COPY src/main/webapp/frontend/tsconfig.json ./
COPY src/main/webapp/frontend/src ./src
RUN npm run build

# Stage 2: Build backend WAR
FROM maven:3.9-eclipse-temurin-21 AS backend
WORKDIR /app
COPY pom.xml ./
RUN mvn -B -ntp dependency:go-offline
COPY src ./src
COPY --from=frontend /app/src/main/webapp/frontend/dist ./src/main/webapp/frontend/dist
RUN mvn -B -ntp clean package -DskipTests

# Stage 3: Run
FROM tomcat:10.1
COPY --from=backend /app/target/22.war /usr/local/tomcat/webapps/ROOT.war
