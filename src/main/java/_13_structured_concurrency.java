// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_13_structured_concurrency.java

import java.util.concurrent.StructuredTaskScope;

void main() throws InterruptedException {
  try(var sts = new StructuredTaskScope<Integer>()) {
    var task1 = sts.fork(() -> {
      Thread.sleep(100);
      return 100;
    });
    var task2 = sts.fork(() -> {
      Thread.sleep(200);
      return 200;
    });
    sts.join();
    System.out.println(task1.get() + " " + task2.get());
  }
}