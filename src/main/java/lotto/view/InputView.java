package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

  private static final int MAX_MONEY = 1_000 * 100;
  private static final int LOTTO_PRICE = 1_000;

  public static int getMoney() {
    System.out.println("구입금액을 입력해 주세요.");

    while (true) {
      try {
        String input = Console.readLine();
        return validateMoney(input);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static List<Integer> getWinningNumbers() {
    System.out.println();
    System.out.println("당첨 번호를 입력해 주세요.");

    while (true) {
      try {
        String input = Console.readLine();
        validateWinningNumber(input);
        return parseNumbers(input);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static int getBonusNumber() {
    System.out.println();
    System.out.println("보너스 번호를 입력해 주세요.");

    while (true) {
      try {
        String input = Console.readLine();
        validateBonusNumber(input);
        return Integer.parseInt(input);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static void validateBonusNumber(String input) {
    if (!input.matches("\\d+")) {
      throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
    }

    int bonusNumber = Integer.parseInt(input);
    if (bonusNumber < 1 || bonusNumber > 45) {
      throw new IllegalArgumentException("[ERROR] 1부터 45까지의 숫자만 입력 가능합니다.");
    }

  }

  private static void validateWinningNumber(String input) {
    if (!input.matches("[\\d,]+")) {
      throw new IllegalArgumentException("[ERROR] 숫자와 쉼표(,)만 입력 가능합니다.");
    }
  }

  private static List<Integer> parseNumbers(String input) {
    return Arrays.stream(input.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
  }

  private static int validateMoney(String input) {
    if (!input.matches("\\d+")) {
      throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
    }
    int money = Integer.parseInt(input);
    if (money >= MAX_MONEY) {
      throw new IllegalArgumentException("[ERROR] 구입 금액은 " + MAX_MONEY + "원 미만이어야 합니다.");
    }
    if (money % LOTTO_PRICE != 0) {
      throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }
    return money;
  }
}
