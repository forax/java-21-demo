// $JAVA_HOME/bin/java --source 21 --enable-preview src/main/java/_10_template_processor.java

import java.util.List;

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

  StringTemplate.Processor<String, RuntimeException> fireProcessor = (StringTemplate templatedString) -> {
    List<String> fragments = templatedString.fragments();
    List<Object> values = templatedString.values();
    System.out.println(STR."fragments:'\{ fragments }' values:'\{ values }'");

    return StringTemplate.interpolate(fragments, values.stream()
        .map(value -> firepower((MilitaryUnit) value))
        .toList());
  };

  System.out.println(fireProcessor."""
      Jane firepower:    \{ jane }
      carrier firepower: \{ carrier } 
      """);
}