#!/bin/bash
mkdir -p my_build
javac -d my_build app/src/main/java/org/example/App.java

mkdir -p my_docs
javadoc -d my_docs -sourcepath app/src/main/java -subpackages org.example

echo "Main-Class: org.example.App" > my_build/manifest.mf
jar cmvf my_build/manifest.mf my_build/App.jar -C my_build .

java -jar my_build/App.jar
