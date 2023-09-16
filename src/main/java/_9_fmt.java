// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_9_fmt.java

import java.util.List;
import static java.util.FormatProcessor.FMT;

interface _9_fmt {

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
    System.out.println(FMT."""
        Jane firepower:    %04d\{ firepower(jane) }
        carrier firepower: %04d\{ firepower(carrier) } 
        """);
  }
}