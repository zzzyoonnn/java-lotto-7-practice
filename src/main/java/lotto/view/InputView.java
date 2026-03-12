package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

  private static final int MAX_MONEY = 1_000 * 100;
  private static final int LOTTO_PRICE = 1_000;

  // 로또 구매 입력받기(1000 단위 아니면 예외 발생 후 재입력)
  public static void getMoney() {
    System.out.println("구입금액을 입력해 주세요.");
    String input = Console.readLine();

    int money = validateMoney(input);
    System.out.println(money + "원을 입력 받았습니다.");
    System.out.println(money / LOTTO_PRICE + "장의 로또를 구매합니다.");

    // LottoGenerator(domain)로 이동
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
