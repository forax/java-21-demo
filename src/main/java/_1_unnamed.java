// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_1_unnamed.java

void main() {  // preview
  System.out.println("hello from an instance main !");
  System.out.println("at runtime, the class name is " + getClass().getName());
  System.out.println("class access flags " + getClass().accessFlags());
}