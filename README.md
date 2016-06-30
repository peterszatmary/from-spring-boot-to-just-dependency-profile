# spring-boot-standalone-dependency-profiles
Maven profiles for building standalone spring-boot app or just simple minimal dependency.


## Purpose
If you need from your Spring Boot application to create just minimalistic dependency jar file
with no Spring in there this project does it for you.

**Maven profiles** :

- **just-dependency** creates dependency jar with no Spring like libs, Tests, Java files in there. Creates just minimalistic jar dependency ready to use.

- **spring-boot-standalone** creates Spring Boot executable application.


We have basic project with JsonDoc here. This is the starting point. One big executable Spring Boot jar file. Now we need exclude files from project on build time and create minimalistic dependency jar file.

### What and when exclude ?

We have to deal with this 3 basic Maven phases :

- **compile phase** compile project source codes
- **test compile phase** compile project tests
- **test phase** run project tests


Project does 4 different exclusion to achieve the goal.

- resource
- Java sources exclusion from compile phase
- Tests exclusion from test compile phase
- Tests exclusion from testing phase


### How to exclude resources

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
### How to exclude Tests from test compile phase
### How to exclude Tests from testing phase


- Project have shared dependencies. ....


