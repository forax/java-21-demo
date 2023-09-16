// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_2_open_types.java
import java.util.List;

/*non-sealed*/ interface MilitaryUnit {
  int firepower();
}
record Soldier(String name, int firepower) implements MilitaryUnit {}
record Carrier(List<MilitaryUnit> units) implements MilitaryUnit {
  public int firepower() {
    return units.stream()
        .mapToInt(u -> u.firepower())
        .sum();
  }
}

void main() {
  var joe = new Soldier("Joe", 200);
  var jane = new Soldier("Jane", 200);
  var carrier = new Carrier(List.of(joe, jane));
  System.out.println("""
      Jane firepower:    %d
      carrier firepower: %d 
      """.formatted(jane.firepower(), carrier.firepower()));
}