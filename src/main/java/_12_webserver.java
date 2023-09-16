// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_12_webserver.java

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.concurrent.Executors;

// firefox localhost:8080/
// firefox localhost:8080/api/

interface _12_webserver {

  static void main(String[] args) throws IOException {
    var path = Path.of("src/main/java").toAbsolutePath();
    var handler = SimpleFileServer.createFileHandler(path);
    var logger = SimpleFileServer.createOutputFilter(System.out, OutputLevel.INFO);
    var server = HttpServer.create(new InetSocketAddress(8080), 10, "/", handler, logger);
    server.createContext("/api/", exchange -> {
      var before = Thread.currentThread();
      try {
        Thread.sleep(3_000);
      } catch (InterruptedException e) {
        throw new AssertionError(e);
      }
      var after = Thread.currentThread();
      exchange.sendResponseHeaders(200, 0);
      var headers = exchange.getResponseHeaders();
      headers.add("Constant-Type", "application/json");
      try (var body = new OutputStreamWriter(exchange.getResponseBody(), StandardCharsets.UTF_8)) {
        body.write(STR."""
            {
              "before-thread": \{ before },
              "after-thread": \{ after }
            }
            """);
      }
    });

    //var executor = Executors.newFixedThreadPool(5);
    var executor = Executors.newVirtualThreadPerTaskExecutor();
    server.setExecutor(executor);
    server.start();
  }
}