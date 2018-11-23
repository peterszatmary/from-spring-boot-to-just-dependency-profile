# spring-boot-standalone-dependency-profiles #

[![Build Status](https://travis-ci.org/peterszatmary/from-spring-boot-to-just-dependency-profile.svg?branch=master)](https://travis-ci.org/peterszatmary/from-spring-boot-to-just-dependency-profile)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ecc28fc4cb6240048cfc3a97ff394537)](https://www.codacy.com/app/peterszatmary/from-spring-boot-to-just-dependency-profile?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=peterszatmary/from-spring-boot-to-just-dependency-profile&amp;utm_campaign=Badge_Grade)

Maven profiles for building standalone spring-boot app or just simple minimal dependency from one project.

## Purpose ##
If you need to create from your Spring Boot application just minimalistic dependency jar file
with no Spring and other stuffs in there, this project does it for you.

## How it works ##

Everything is about [Maven](https://maven.apache.org/) pom.xml files and 2 simple [profiles](http://maven.apache.org/guides/introduction/introduction-to-profiles.html) that could everyone customize for yourself.

**Maven profiles** :

-   **just-dependency** creates dependency jar with no Spring like libraries, Tests, Java files in there. Creates just minimalistic jar dependency ready to use.

-   **spring-boot-standalone** creates Spring Boot executable application.


We have basic project with JsonDoc here. This is the starting point. One big executable Spring Boot jar file. Now we need exclude files from project on build time and create minimalistic dependency jar file.

### What and when exclude ###

We have to deal with this 3 basic Maven phases :

-   **compile phase** compile project source codes
-   **test compile phase** compile project tests
-   **test phase** run project tests

Project does 4 different exclusion to achieve the goal.

-   **resources** exclusion
-   **Java sources** exclusion from compile phase
-   **Tests exclusion** from test compile phase
-   **Tests exclusion** from testing phase

All exclusions are done in just-dependency profile of cource.

### How to exclude resources ###

In build tag 

```xml
<resources>
	<resource>
		<directory>src/main/resources</directory>
		<excludes>
			<exclude>application.properties</exclude>
			<!-- add here more if you need exclude additional files -->
		</excludes>
	<filtering>false</filtering>
	</resource>
</resources>
```

Because project is simple we have just one properties file that we dont want to have in our minimalistic dependency jar. In tag **excludes** you can add as many exclusions as you would like to have. 

### How to exclude Java sources from compile phase ###

Exclusion is done with [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/).
On example is excluding every single java file with prefix name Spring. You can write your own exclusion patterns based on other strategies. For example create directory and exclude everything in there.

All excluded Java sources are not compiled (that means you dont need dependencies for it) when jar file is created.

In tag **excludes** you can add as many exclusions as you would like to have. 

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-compiler-plugin</artifactId>
	<version>${compiler.plugin.version}</version>

	<configuration>
	    <excludes>
	        <exclude>**/Spring*.java</exclude><!-- by prefix strategy -->
	        <!-- add here more if you need exclude additional files -->
	    </excludes>
	</configuration>
</plugin>
```

### How to exclude Tests from test compile phase ###

Exclusion is done also with [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/).
In this example are excluded all tests in directory standalone. This test are not compiled (that means you dont need dependencies for it) when jar file is created.

In tag **excludes** you can add as many exclusions as you would like to have. 

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-compiler-plugin</artifactId>
	<version>${compiler.plugin.version}</version>

	<executions>
	    <execution>
	        <id>default-testCompile</id>
	        <phase>test-compile</phase>
	        <goals>
	            <goal>testCompile</goal>
	        </goals>
	        <configuration>
	            <testExcludes>
	                <exclude>**/standalone/*.java</exclude>
	                <!-- add here more if you need exclude additional files -->
	            </testExcludes>
	        </configuration>
	    </execution>
	</executions>
</plugin>
```

### How to exclude Tests from testing phase ###

Exclusion is done with [maven-surefire-plugin](http://maven.apache.org/surefire/maven-surefire-plugin/). Ignored (not ran) are all tests in standalone directory.

In tag **excludes** you can add as many exclusions as you would like to have. 

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<version>${surefire.version}</version>
	<configuration>
	    <excludes>
	        <exclude>**/src/test/java/**/standalone/**.java</exclude><!-- in directory strategy -->
	    </excludes>
	</configuration>
</plugin>
```

## Notes ##

-   Some dependencies are just for spring-boot-standalone version. just-dependency profile have no dependencies here because all that this profile needs are present like shared dependencies accross profiles. See [pom file](https://github.com/peterszatmary/spring-boot-standalone-dependency-profiles/blob/master/pom.xml). If  you need dependencies that are just for just-dependency profile, fill free add them with dependencies tag.

## How to build them ##

From from your preferred IDE or from command line.

Standalone Spring Boot executable jar file 

```bash
mvn clean install -P spring-boot-standalone
```

Dependency jar file ready to use like dependency in your projects

```bash
mvn clean install -P just-dependency
```

## Result after just-dependency profile ##

After build is created file **just-dependency.jar**.

```shell
├── com
│   └── szatmary
│       └── peter
│           ├── ApplicationController.class
│           ├── Car.class
│           └── Owner.class
└── META-INF
    ├── MANIFEST.MF
    └── maven
        └── com.szatmary.peter
            └── spring-boot-standalone-dependency-profiles
                ├── pom.properties
                └── pom.xml

7 directories, 6 files
```


## Result after spring-boot-standalone profile ##

After build is created file **standalone-spring-boot.jar**.

```shell
├── application.properties
├── com
│   └── szatmary
│       └── peter
│           ├── ApplicationController.class
│           ├── Car.class
│           ├── Owner.class
│           └── SpringbootJsondocApplication.class
├── lib
│   ├── annotations-2.0.1.jar
│   ├── aopalliance-1.0.jar
│   ├── classmate-1.1.0.jar
│   ├── guava-15.0.jar
│   ├── hibernate-validator-5.2.4.Final.jar
│   ├── jackson-annotations-2.6.6.jar
│   ├── jackson-core-2.6.6.jar
│   ├── jackson-databind-2.6.6.jar
│   ├── javassist-3.18.1-GA.jar
│   ├── jboss-logging-3.3.0.Final.jar
│   ├── jcl-over-slf4j-1.7.21.jar
│   ├── jsondoc-core-1.2.15.jar
│   ├── jsondoc-springmvc-1.2.15.jar
│   ├── jsondoc-ui-webjar-1.2.15.jar
│   ├── jul-to-slf4j-1.7.21.jar
│   ├── log4j-over-slf4j-1.7.21.jar
│   ├── logback-classic-1.1.7.jar
│   ├── logback-core-1.1.7.jar
│   ├── lombok-1.16.8.jar
│   ├── reflections-0.9.10.jar
│   ├── slf4j-api-1.7.21.jar
│   ├── snakeyaml-1.16.jar
│   ├── spring-aop-4.2.6.RELEASE.jar
│   ├── spring-beans-4.2.6.RELEASE.jar
│   ├── spring-boot-1.3.5.RELEASE.jar
│   ├── spring-boot-autoconfigure-1.3.5.RELEASE.jar
│   ├── spring-boot-starter-1.3.5.RELEASE.jar
│   ├── spring-boot-starter-jsondoc-1.2.15.jar
│   ├── spring-boot-starter-logging-1.3.5.RELEASE.jar
│   ├── spring-boot-starter-tomcat-1.3.5.RELEASE.jar
│   ├── spring-boot-starter-validation-1.3.5.RELEASE.jar
│   ├── spring-boot-starter-web-1.3.5.RELEASE.jar
│   ├── spring-context-4.2.6.RELEASE.jar
│   ├── spring-core-4.2.6.RELEASE.jar
│   ├── spring-expression-4.2.6.RELEASE.jar
│   ├── spring-web-4.2.6.RELEASE.jar
│   ├── spring-webmvc-4.2.6.RELEASE.jar
│   ├── tomcat-embed-core-8.0.33.jar
│   ├── tomcat-embed-el-8.0.33.jar
│   ├── tomcat-embed-logging-juli-8.0.33.jar
│   ├── tomcat-embed-websocket-8.0.33.jar
│   └── validation-api-1.1.0.Final.jar
├── META-INF
│   ├── MANIFEST.MF
│   └── maven
│       └── com.szatmary.peter
│           └── spring-boot-standalone-dependency-profiles
│               ├── pom.properties
│               └── pom.xml
└── org
    └── springframework
        └── boot
            └── loader
                ├── archive
                │   ├── Archive.class
                │   ├── Archive$Entry.class
                │   ├── Archive$EntryFilter.class
                │   ├── Archive$EntryRenameFilter.class
                │   ├── ExplodedArchive$1.class
                │   ├── ExplodedArchive.class
                │   ├── ExplodedArchive$FileEntry.class
                │   ├── ExplodedArchive$FileNotFoundURLConnection.class
                │   ├── ExplodedArchive$FilteredURLStreamHandler.class
                │   ├── FilteredArchive$1.class
                │   ├── FilteredArchive$2.class
                │   ├── FilteredArchive.class
                │   ├── JarFileArchive$1.class
                │   ├── JarFileArchive.class
                │   └── JarFileArchive$JarFileEntry.class
                ├── data
                │   ├── ByteArrayRandomAccessData.class
                │   ├── RandomAccessData.class
                │   ├── RandomAccessDataFile.class
                │   ├── RandomAccessDataFile$DataInputStream.class
                │   ├── RandomAccessDataFile$FilePool.class
                │   └── RandomAccessData$ResourceAccess.class
                ├── ExecutableArchiveLauncher$1.class
                ├── ExecutableArchiveLauncher.class
                ├── InputArgumentsJavaAgentDetector$1.class
                ├── InputArgumentsJavaAgentDetector.class
                ├── jar
                │   ├── Bytes.class
                │   ├── CentralDirectoryEndRecord.class
                │   ├── Handler.class
                │   ├── JarEntry.class
                │   ├── JarEntryData.class
                │   ├── JarEntryFilter.class
                │   ├── JarFile$1.class
                │   ├── JarFile$2.class
                │   ├── JarFile.class
                │   ├── JarURLConnection$1.class
                │   ├── JarURLConnection.class
                │   ├── JarURLConnection$JarEntryName.class
                │   └── ZipInflaterInputStream.class
                ├── JarLauncher.class
                ├── JavaAgentDetector.class
                ├── LaunchedURLClassLoader$1.class
                ├── LaunchedURLClassLoader.class
                ├── LaunchedURLClassLoader$Java7LockProvider.class
                ├── LaunchedURLClassLoader$LockProvider.class
                ├── LaunchedURLClassLoader$ResourceEnumeration.class
                ├── Launcher.class
                ├── MainMethodRunner.class
                ├── PropertiesLauncher$1.class
                ├── PropertiesLauncher$ArchiveEntryFilter.class
                ├── PropertiesLauncher.class
                ├── PropertiesLauncher$PrefixMatchingArchiveFilter.class
                ├── util
                │   ├── AsciiBytes.class
                │   └── SystemPropertyUtils.class
                └── WarLauncher.class

16 directories, 104 files
```

## How to run ##


### Without docker ###

After build successful maven build

```bash
java -jar standalone-spring-boot.jar
localhost:8080/jsondoc-ui.html?url=jsondoc
```

### With Docker ###

Project has also Dockers. You can choose from docker for creating development environment or
 deploying the app.

#### For development ####

```bash
$  ./bin/dev/docker_build.sh
$  ./bin/dev/docker_start.sh
$$ mvn clean install -P spring-boot-standalone
$$ java -jar ./target/standalone-spring-boot.jar
$ localhost:8080/jsondoc-ui.html?url=jsondoc
```

#### For deployment ####

```bash
$  ./bin/deploy/docker_build.sh
$  ./bin/deploy/docker_start.sh
$  localhost:8080/jsondoc-ui.html?url=jsondoc
```

![jsondoc](https://github.com/peterszatmary/just-like-that/blob/master/imgs/spring-boot-standalone-dependency-profiles/jsondoc.png)