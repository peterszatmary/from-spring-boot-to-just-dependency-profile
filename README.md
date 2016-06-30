# spring-boot-standalone-dependency-profiles
Maven profiles for building standalone spring-boot app or just simple minimal dependency from same project.


## Purpose
If you need to create from your Spring Boot application just minimalistic dependency jar file
with no Spring and other stuffs in there, this project does it for you.

## How it works

Everything is about [Maven](https://maven.apache.org/) pom.xml files and 2 simple [profiles](http://maven.apache.org/guides/introduction/introduction-to-profiles.html) that could everyone customize for yourself.


**Maven profiles** :

- **just-dependency** creates dependency jar with no Spring like libraries, Tests, Java files in there. Creates just minimalistic jar dependency ready to use.

- **spring-boot-standalone** creates Spring Boot executable application.


We have basic project with JsonDoc here. This is the starting point. One big executable Spring Boot jar file. Now we need exclude files from project on build time and create minimalistic dependency jar file.

### What and when exclude ?

We have to deal with this 3 basic Maven phases :

- **compile phase** compile project source codes
- **test compile phase** compile project tests
- **test phase** run project tests


Project does 4 different exclusion to achieve the goal.

- **resource**
- **ava sources** exclusion from compile phase
- **Tests exclusion** from test compile phase
- **Tests exclusion** from testing phase

All exclusions are done in just-dependency profile of cource.

### How to exclude resources

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


### How to exclude Java sources from compile phase

Exclusion is done with [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/).
On example is excluding every single java file with prefix name Spring. You can write your own exclusion patterns based on other strategies. For example create directory and exclude everything in there.

All excluded Java sources are not compiled (that means you dont need dependencies for it) when jar file is created.

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


### How to exclude Tests from test compile phase

Exclusion is done also with [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/).
In this example are excluded all tests in directory standalone. This test are not compiled (that means you dont need dependencies for it) when jar file is created.

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


### How to exclude Tests from testing phase


Exclusion is done with [maven-surefire-plugin](http://maven.apache.org/surefire/maven-surefire-plugin/). Ignored are all tests in standalone directory.

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


-----------------------



- Some dependencies are just for spring-boot-standalone version. just-dependency have no dependencies here because all that this profile needs are present like shared dependencies accross profiles. See pom file. TODO



## How to run it


From from your preffered IDE or from command line.

Standalone Spring Boot executable jar file 

```shell
mvn clean install -P spring-boot-standalone
```

Dependency jar file

```shell
mvn clean install -P just-dependency
```


## Result after just-dependency profile

....

## Result after spring-boot-standalone profile

....
