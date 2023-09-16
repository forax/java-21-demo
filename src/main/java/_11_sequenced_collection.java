// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_11_sequenced_collection.java

import java.util.List;

interface _11_sequenced_collection {

  sealed interface MilitaryUnit { }
  record Soldier(String name, int firepower) implements MilitaryUnit { }
  record Carrier(List<MilitaryUnit> units) implements MilitaryUnit { }

  static void main(String[] args) {
    var joe = new Soldier("Joe", 200);
    var jane = new Soldier("Jane", 200);
    var carrier = new Carrier(List.of(joe, jane));

    System.out.println("first " + carrier.units().getFirst());
    System.out.println("last " + carrier.units().getLast());

    for (var unit : carrier.units().reversed()) {
      System.out.println("unit " + unit);
    }
  }
}