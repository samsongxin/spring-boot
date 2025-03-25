FROM --platform=linux/amd64 expertswe-external-base:20250227-v3

# install maven
ARG MAVEN_VERSION=3.9.9
RUN curl -fsSL "https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" | tar -xzC /usr/share/

RUN mv /usr/share/apache-maven-${MAVEN_VERSION} /usr/share/maven

ENV MAVEN_HOME /usr/share/maven
ENV PATH $MAVEN_HOME/bin:$PATH

RUN mvn -version



# Define variables
ARG ZIP_FILE_NAME=held_out_tests

# Derived & constant variables
ARG REPO_ZIP_FILE=${ZIP_FILE_NAME}.zip
ENV REPO_DIR /root/repo
ENV WORK_DIR ${REPO_DIR}/spring-boot/spring-boot-project/spring-boot-rewards


# Create working directory and copy repo archive
RUN mkdir -p ${WORK_DIR}
COPY ${REPO_ZIP_FILE} ${REPO_DIR}/${REPO_ZIP_FILE}

# Extract the repository
RUN unzip /root/repo/${REPO_ZIP_FILE} -d /root/repo/


WORKDIR ${WORK_DIR}

# Download dependencies (this layer is cached)
RUN mvn dependency:go-offline

# RUN mvn clean test

WORKDIR /root
RUN service redis-server start

# cd /root/repo/spring-boot-dev/spring-boot-project/spring-boot-rewards
# mvn test -Dtest=LRUCustomerStateCacheTest




