# About Task:
Your program must accept two filenames as command-line parameters. These files will contain 7-bit 
	ASCII text, and each line may consist of an IP address, followed by a colon, followed by a comma-separated
	list of numbers. The two files should be joined on IP address and the numbers from each file should be appended
    and returned, sorted and without duplicates. The results should be written to stdout as the IP address 
    followed by a colon, followed by a comma separated list of the numbers.  

This repository holds projects that together comprise the Ab bank IP merge for the Assessment.

## Build Prerequisites

* JDK 1.8
* Spring STS 3.8.x (https://spring.io/tools/sts/all) 
* Git Shell (https://git-for-windows.github.io/) or SourceTree (https://www.sourcetreeapp.com/) for Cloning & pushing the code changes. 
* Maven 3.x
* Proxy setup to download dependencies from open source repositories
* Open Source or GitShell Command Line Interface

## Build Instructions

1. Browse to your preferred directory and run below command:

    git clone https://github.com/AmitKrMishra/ab-ip-merger.git

2. Build via this command:
   
    mvn clean install

How to build locally
--------------------

- to build and install

	mvn clean install

- Run all the unit test classes.

    mvn test	
	
- To check java docs are working
   
    mvn javadoc:javadoc

You can view javadocs in the path provided in console

- Fixing java docs	
	
	mvn javadoc:fix -DfixTags="param,return,throws,link"


-Check html page under here
    
	ab-ip-merger\target\site\jacoco\ab.bank.ip.model\index.source.html    
	
- Check style

	mvn checkstyle:check	


