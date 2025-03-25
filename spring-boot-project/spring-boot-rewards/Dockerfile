FROM --platform=linux/amd64 expertswe-external-base:20250227-v3

# install maven
ARG MAVEN_VERSION=3.9.9
RUN curl -fsSL "https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" | tar -xzC /usr/share/

RUN mv /usr/share/apache-maven-${MAVEN_VERSION} /usr/share/maven

ENV MAVEN_HOME /usr/share/maven
ENV PATH $MAVEN_HOME/bin:$PATH

RUN mvn -version


# Derived & constant variables
ARG REPO_ZIP_FILE=held_out_tests.zip
ENV REPO_DIR /root/repo
ENV POM_DIR ${REPO_DIR}/spring-boot/spring-boot-project/spring-boot-rewards


# Create working directory and copy repo archive
# RUN mkdir -p ${POM_DIR}
COPY ${REPO_ZIP_FILE} ${REPO_DIR}/${REPO_ZIP_FILE}

# Extract the repository
RUN unzip /root/repo/${REPO_ZIP_FILE} -d /root/repo/

WORKDIR ${POM_DIR}
# Download dependencies (this layer is cached)
RUN mvn dependency:go-offline

# RUN mvn clean test

WORKDIR /root
RUN service redis-server start

WORKDIR ${POM_DIR}

# docker run -it held_out_test:1.0
# mvn test -Dtest=GeneralTests#isWrittenInJava





