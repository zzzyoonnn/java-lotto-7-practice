package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

  private static final int MAX_MONEY = 1_000 * 100;
  private static final int LOTTO_PRICE = 1_000;

  public static int getMoney() {
    System.out.println("구입금액을 입력해 주세요.");
    String input = Console.readLine();
    return validateMoney(input);
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
