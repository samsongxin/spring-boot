FROM --platform=linux/amd64 expertswe-external-base:20250227-v3

ARG MAVEN_VERSION=3.9.9

RUN curl -fsSL "https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" | tar -xzC /usr/share/

RUN mv /usr/share/apache-maven-${MAVEN_VERSION} /usr/share/maven

ENV MAVEN_HOME /usr/share/maven
ENV PATH $MAVEN_HOME/bin:$PATH

RUN mvn -version


# Define variables
ENV WORK_DIR /root/repo

# Create working directory and copy repo archive
RUN mkdir -p ${WORK_DIR}
COPY pom.xml ${WORK_DIR}
COPY src ${WORK_DIR}/src

# Extract the repository
WORKDIR ${WORK_DIR}

# Download dependencies (this layer is cached)
RUN mvn dependency:go-offline

RUN mvn clean test

