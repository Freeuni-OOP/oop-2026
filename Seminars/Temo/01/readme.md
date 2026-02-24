- კოდის კომპილაცია  
```bash
javac Main.java  
```
- კოდის გაშვება  
```bash
java Main  
```
- jar არქივის შექმნა  
```bash
jar cvfe main.jar Main *.class  
```

>cvfe გადაცემული პარამეტრები ნიშნავს შემდეგს  
>
>>c - Create a new JAR archive.  
>>v - Generates verbose output to standard output. See Examples.  
>>f - Sets the file specified by the jarfile operand to be the name of the JAR file that is created (c), updated (u), extracted (x) from, or viewed (t). Omitting the f option and the jarfile operand instructs the jar command to accept the JAR file name from stdin (for x and t) or send the JAR file to stdout (for c and u).  
>>e - Sets the class specified by the entrypoint operand to be the entry point for a standalone Java application bundled into an executable JAR file. The use of this option creates or overrides the Main-Class attribute value in the manifest file. The e option can be used when creating (c) or updating (u) the JAR file.  

- შექმნილი jar არქივის გაშვება  
```bash
java -jar main.jar  
```

- შექმნილი jar არქივის გაშვება სხვა დამხმარე jar-ის პარამეტრად გადაცემა  
```bash
--classpath or -cp  
windows: java --classpath "main.jar;hello.jar" Main  
linux: java -cp "main.jar:hello.jar" Main  
```
