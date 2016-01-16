# Multi Threaded Cypher Breaker

##Synopsis
Rail Fence cypher, encryption, decryption and cypher breaking using multithreading in Java. Utilizes the Java Collections & Concurrent APIs. JUnit is used to test every method in the application and Log4J has been added (not yet implemented) for a more robust logging system. This program uses a highly abstracted and loosly coupled style so as to create a more robust Object Orientated Program. 

![alt tag](http://johnmalcolmdesign.com/design.png)

##Packages 
**src/**
 - ie.gmit.sw: Application entry points and global classes.
 - ie.gmit.sw.cryptography: Cryptographic classes that relate to encryption, decryption and decyphering.
 - ie.gmit.sw.io: Input/output classes should be stored here, including network, user and file I/O.
 - ie.gmit.sw.result: Results based on n-gram scoring of decyphered text, ngram and text scoring classes.
 - ie.gmit.sw.uml: UML Diagram.

**test/**
 - ie.gmit.sw.cryptography: Cryptography test cases.
 - ie.gmit.sw.io: IO test cases.
 - ie.gmit.sw.result: Result test cases.
 
##Technologies
 - Language: Java
 - Build tool: Ant
 - Logging: Log4J
 - Unit testing: JUnit
 - UML: Object Aid

##Building Application
Application should be built via the build.xml ant file. Build phases below:
- **clean:** Delete all generated classes and files. (depends on clean)
- **init:** Creates dist folder for compiled code, jars and docs if it does not exist.
- **compile**: Compile source code. This does not include test cases. (depends on init)
- **junit**: Compile and run test cases. Prints reports for each test class to reports folder. (depends on compile)
- **archive**: Create .jars for running application. (depends on junit passing)
- **docs**: Generate Javadocs. (depends on archive)
- **deploy (default)**: Compress classes and documentation into Zip and compressed tarball. (depends on docs)

*Build and see generated Javadocs for more info on each class and method.*

*JAX-RS based REST class that allows for HTTP access is currently in development and will be added to the ie.gmit.sw.io package in version 2.0.*
*Front end website repo: https://github.com/johnmalcolm/Rail-Fence-Cypher-Website*
