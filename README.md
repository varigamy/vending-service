# Introduction 
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Installation process

Download and install the Java package on your machine (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

Add the path of the Java bin directory to the System variable PATH (e.g. C:/Program Files/Java/jdk1.8/bin). See here https://www.computerhope.com/issues/ch000549.htm how do add Environmental variables in Windows.

Execute the command (via the cmd):

**java -version**

The version of the installed JVM should be displayed.

Download and unpack the latest Apache maven Tool (https://maven.apache.org/). E.g. you unpacked it to the C:/apache-maven.
Add the bin directory to theSystem PATH variable (e.g. C:/Program Files/apache-maven/bin). Create a folder where all maven libraries will be downloaded and stored (e.g. C:/maven).
Open your conf maven directory (C:/apache-maven/conf) and open setting.xml file. Find and uncomment '<localRepository>' line and set the path to 'C:/maven' (depending on what folder you created in the previous step)

**<localRepository>C:/maven</localRepository>**


2.	Software dependencies
3.	Latest releases
4.	API references

# Build and Test
Go to the *vending-machine* (downloaded from the repository). You'll see the **pom.xml** file in the root directory.

Run (in cmd) the command:

**mvn install**

This will compile and build the project (the project will be packaged and archived in a jar file and created in the maven directory (in our case it might be C:/maven/com/vending/vending-machine/1.0.0 depending on what path we set to the **localRepository** in **settings.xml**. See above).

Wait for several minutes (until all libs and dependencies are downloaded from the Maven central Repository https://search.maven.org/). The project is compiled and packaged to the C:/maven/com/vending/vending-machine/1.0.0 directory: this directory is specified in the **pom.xml** file.

**Important**
Run **mvn install** always after you take changes from the git repository for the corresponding project (vending-machine). Sometimes when you pick up new changes
you will have to run:

**mvn clean install**

When the **maven install** operation is completed go to the C:/maven/com/vending/vending-machine/1.0.0. Open your command line there and run:

**java -jar vending-machine-1.0.0.jar**
 
This will start the embedded Tomcat servlet container and your App is deployed after a couple of seconds.

Using any REST Client call the GET request [http://localhost:8081/api/change/1178](http://localhost:8081/api/change/1178) (1178 in cents)

#Examples
**Get change**
```
Request 
GET http://localhost:8081/api/change/4

Response
[
  {
    "denomination": 2,
    "name": "Two cents"
  },
  {
    "denomination": 2,
    "name": "Two cents"
  }
]
```

# Contribute
TODO: Explain how other users and developers can contribute to make your code better.