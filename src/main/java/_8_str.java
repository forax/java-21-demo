// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_8_str.java

import java.util.List;

interface _8_str {

  sealed interface MilitaryUnit { }
  record Soldier(String name, int firepower) implements MilitaryUnit { }
  record Carrier(List<MilitaryUnit> units) implements MilitaryUnit { }

  static int firepower(MilitaryUnit unit) {
    return switch (unit) {
      case Soldier(_, var firepower) -> firepower;
      case Carrier(var units) -> units.stream()
          .mapToInt(u -> firepower(u))
          .sum();
    };
  }

  static void main(String[] args) {
    var joe = new Soldier("Joe", 200);
    var jane = new Soldier("Jane", 200);
    var carrier = new Carrier(List.of(joe, jane));
    System.out.println(STR."""
        Jane firepower:    \{ firepower(jane) }
        carrier firepower: \{ firepower(carrier) } 
        """);
  }
}