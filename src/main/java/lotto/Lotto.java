package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    validate(numbers);
    this.numbers = numbers.stream()
            .sorted()
            .collect(Collectors.toList());
  }

  private void validate(List<Integer> numbers) {
    if (numbers.size() != 6) {
      throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    if (new HashSet<>(numbers).size() != numbers.size()) {
      throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }
  }

  public List<Integer> getNumbers() {
    return Collections.unmodifiableList(numbers);
  }
}
