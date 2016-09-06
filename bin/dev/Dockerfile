FROM gliderlabs/alpine:3.4
MAINTAINER Peter Szatmary <peter.szatmary@gmail.com>

ENV REFRESHED_AT 2016-09-06

ENV MAVEN_VERSION 3.3.9
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV M2_HOME /home/fsbtjdp/etc/apache-maven-$MAVEN_VERSION
ENV M2 $M2_HOME/bin
ENV PATH $PATH:$M2

RUN apk-install bash ca-certificates openjdk8 git wget

ENV JAVA_HOME /usr/lib/jvm/default-jvm

RUN adduser fsbtjdp -h /home/fsbtjdp -s /bin/bash -D
USER fsbtjdp
WORKDIR /home/fsbtjdp/
RUN mkdir etc
WORKDIR /home/fsbtjdp/etc

RUN wget http://tux.rainside.sk/apache/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz
RUN tar -xvf apache-maven-$MAVEN_VERSION-bin.tar.gz
RUN rm -r apache-maven-$MAVEN_VERSION-bin.tar.gz

WORKDIR /home/fsbtjdp
EXPOSE 8080

CMD ["/bin/sh"]