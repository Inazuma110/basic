run:
	rm -fr newlang4/*.class
	javac newlang4/Main.java
	java newlang4.Main test.bas
clean:
	rm -fr newlang4/*.class
compile:
	javac newlang4/Main.java
