// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_4_sealed_types_switch.java

import java.util.List;

sealed interface MilitaryUnit { }
record Soldier(String name, int firepower) implements MilitaryUnit { }
record Carrier(List<MilitaryUnit> units) implements MilitaryUnit { }

int firepower(MilitaryUnit unit) {
  return switch (unit) {
    case Soldier soldier -> soldier.firepower();
    case Carrier carrier -> carrier.units().stream()
        .mapToInt(this::firepower)
        .sum();
  };
}

void main() {
  var joe = new Soldier("Joe", 200);
  var jane = new Soldier("Jane", 200);
  var carrier = new Carrier(List.of(joe, jane));
  System.out.printf("""
      Jane firepower:    %d
      carrier firepower: %d
      """, firepower(jane), firepower(carrier));
}