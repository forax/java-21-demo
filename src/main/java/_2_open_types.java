// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_2_open_types.java
import java.util.List;

interface _2_open_types {

  /*non-sealed*/ interface MilitaryUnit {
    int firepower();
  }

  record Soldier(String name, int firepower) implements MilitaryUnit {
  }

  record Carrier(List<MilitaryUnit> units) implements MilitaryUnit {
    public int firepower() {
      return units.stream()
          .mapToInt(MilitaryUnit::firepower)
          .sum();
    }
  }

  static void main(String[] args) {
    var joe = new Soldier("Joe", 200);
    var jane = new Soldier("Jane", 200);
    var carrier = new Carrier(List.of(joe, jane));
    System.out.printf("""
        Jane firepower:    %d
        carrier firepower: %d
        """, jane.firepower(), carrier.firepower());
  }
}