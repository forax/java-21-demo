// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_9_template_processor_fmt.java

import java.util.List;
import static java.util.FormatProcessor.FMT;

sealed interface MilitaryUnit { }
record Soldier(String name, int firepower) implements MilitaryUnit {}
record Carrier(List<MilitaryUnit> units) implements MilitaryUnit {}

int firepower(MilitaryUnit unit) {
  return switch(unit) {
    case Soldier(_, var firepower) -> firepower;
    case Carrier(var units) -> units.stream()
        .mapToInt(u -> firepower(u))
        .sum();
  };
}

void main() {
  var joe = new Soldier("Joe", 200);
  var jane = new Soldier("Jane", 200);
  var carrier = new Carrier(List.of(joe, jane));
  System.out.println(FMT."""
      Jane firepower:    %04d\{ firepower(jane) }
      carrier firepower: %04d\{ firepower(carrier) } 
      """);
}