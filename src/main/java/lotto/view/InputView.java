package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.domain.InputValidator;

public class InputView {

  private static final InputValidator inputValidator = new InputValidator();

  public int getMoney() {
    System.out.println("구입금액을 입력해 주세요.");

    while (true) {
      try {
        String input = Console.readLine();
        return inputValidator.validateMoney(input);
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
        inputValidator.validateWinningNumber(input);
        return inputValidator.parseNumbers(input);
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
        inputValidator.validateBonusNumber(input);
        return Integer.parseInt(input);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
