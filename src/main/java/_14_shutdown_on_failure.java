// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_14_shutdown_on_failure.java

import java.util.concurrent.StructuredTaskScope;
import java.io.IOException;

void main() throws InterruptedException {
  try(var sts = new StructuredTaskScope.ShutdownOnFailure()) {
    var task1 = sts.fork(() -> {
      Thread.sleep(100);
      return 100;
    });
    var task2 = sts.<Integer>fork(() -> {
      Thread.sleep(200);
      //throw new IOException();
      return 200;
    });
    sts.join();

    var task3 = sts.fork(() -> {
      Thread.sleep(300);
      return task1.get() + task2.get();
    });
    sts.join();

    sts.throwIfFailed(RuntimeException::new);
    System.out.println(task3.get());
  }
}