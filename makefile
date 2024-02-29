
build: footballProject.class

footballProject.class: footballProject.java
	javac footballProject.java

run: footballProject.class
	java -cp .:mssql-jdbc-11.2.0.jre18.jar footballProject

clean:
	rm footballProject.class
