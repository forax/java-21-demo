// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_14_structured_concurrency.java

import java.util.concurrent.StructuredTaskScope;

void main() throws InterruptedException {
  try (var scope = new StructuredTaskScope<Integer>()) {
    var task1 = scope.fork(() -> {
      Thread.sleep(100);
      return 100;
    });
    var task2 = scope.fork(() -> {
      Thread.sleep(200);
      return 200;
    });
    scope.join();
    System.out.println(task1.get() + " " + task2.get());
  }
}