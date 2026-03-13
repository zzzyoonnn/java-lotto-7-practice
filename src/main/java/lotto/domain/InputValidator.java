package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidator {

  private static final int MAX_MONEY = 1_000 * 100;
  private static final int LOTTO_PRICE = 1_000;

  public int validateMoney(String input) {
    if (!input.matches("\\d+")) {
      throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
    }
    int money = Integer.parseInt(input);

    if (money <= 0) {
      throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 이상이어야 합니다.");
    }

    if (money >= MAX_MONEY) {
      throw new IllegalArgumentException("[ERROR] 구입 금액은 " + MAX_MONEY + "원 미만이어야 합니다.");
    }
    if (money % LOTTO_PRICE != 0) {
      throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }
    return money;
  }

  public String validateWinningNumber(String input) {
    if (!input.matches("[\\d,]+")) {
      throw new IllegalArgumentException("[ERROR] 숫자와 쉼표(,)만 입력 가능합니다.");
    }

    return input;
  }

  public void validateBonusNumber(String input) {
    if (!input.matches("\\d+")) {
      throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
    }

    int bonusNumber = Integer.parseInt(input);
    if (bonusNumber < 1 || bonusNumber > 45) {
      throw new IllegalArgumentException("[ERROR] 1부터 45까지의 숫자만 입력 가능합니다.");
    }
  }

  public List<Integer> parseNumbers(String input) {
    return Arrays.stream(input.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
  }
}
