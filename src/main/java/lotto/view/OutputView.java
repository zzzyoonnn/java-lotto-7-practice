package lotto.view;

import java.util.List;
import lotto.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class OutputView {

  public static void printLottoNumbers(List<Lotto> lottos) {
    StringBuilder sb = new StringBuilder();
    sb.append('\n').append(lottos.size()).append("개를 구매했습니다.").append('\n');
    for (Lotto lotto : lottos) {
      sb.append(lotto.getNumbers()).append('\n');
    }
    System.out.print(sb);
  }

  public static void printResult(LottoResult lottoResult, int money) {
    StringBuilder sb = new StringBuilder();
    sb.append("당첨 통계").append('\n');
    sb.append("---").append('\n');
    sb.append("3개 일치 (5,000원) - ").append(lottoResult.getResults().get(Rank.FIFTH)).append("개").append('\n');
    sb.append("4개 일치 (50,000원) - ").append(lottoResult.getResults().get(Rank.FOURTH)).append("개").append('\n');
    sb.append("5개 일치 (1,500,000원) - ").append(lottoResult.getResults().get(Rank.THIRD)).append("개").append('\n');
    sb.append("5개 일치, 보너스 볼 일치 (30,000,000원) - ").append(lottoResult.getResults().get(Rank.SECOND)).append("개").append('\n');
    sb.append("6개 일치 (2,000,000,000원) - ").append(lottoResult.getResults().get(Rank.FIRST)).append("개").append('\n');
    sb.append("총 수익률은 ").append(lottoResult.calculateProfitRate(money)).append("%입니다.").append('\n');

    System.out.print(sb);
  }
}
