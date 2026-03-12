package lotto.view;

import java.util.List;
import lotto.Lotto;

public class OutputView {

  public static void printLottoNumbers(List<Lotto> lottos) {
    StringBuilder sb = new StringBuilder();
    sb.append('\n').append(lottos.size()).append("개를 구매했습니다.").append('\n');
    for (Lotto lotto : lottos) {
      sb.append(lotto.getNumbers()).append('\n');
    }
    System.out.print(sb);
  }
}
