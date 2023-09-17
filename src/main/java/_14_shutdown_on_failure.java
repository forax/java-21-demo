// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_14_shutdown_on_failure.java

import java.util.concurrent.StructuredTaskScope;
import java.io.IOException;

interface _14_shutdown_on_failure {

  static void main(String[] args) throws InterruptedException {
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      var task1 = scope.fork(() -> {
        Thread.sleep(100);
        return 100;
      });
      var task2 = scope.<Integer>fork(() -> {
        Thread.sleep(200);
        //throw new IOException();
        return 200;
      });
      scope.join();

      var task3 = scope.fork(() -> {
        Thread.sleep(300);
        return task1.get() + task2.get();
      });
      scope.join();

      scope.throwIfFailed(RuntimeException::new);
      System.out.println(task3.get());
    }
  }
}