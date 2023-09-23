// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_12_sequenced_collection_dragon.java

import java.util.LinkedHashMap;

void main() {
  var map = new LinkedHashMap<>(0, 0.75f, true);
  map.put("foo", 3);
  map.put("bar", 42);
  System.out.println(map.get("foo"));  // 3
  System.out.println(map.firstEntry());  // ??
}