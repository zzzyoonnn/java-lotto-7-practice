package lotto.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lotto.Lotto;

public class LottoResult {

  private final Map<Rank, Integer> results = new LinkedHashMap<>();

  public LottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
    LottoMatcher matcher = new LottoMatcher();
    for (Rank rank : Rank.values()) {
      results.put(rank, 0);
    }
    for (Lotto lotto : lottos) {
      Rank rank = matcher.match(lotto, winningLotto);
      results.put(rank, results.get(rank) + 1);
    }
  }

  public Map<Rank, Integer> getResults() {
    return Collections.unmodifiableMap(results);
  }

  public double calculateProfitRate(int money) {
    long totalPrize = results.entrySet().stream()
            .mapToLong(e -> (long) e.getKey().getPrize() * e.getValue())
            .sum();
    double profitRate = (double) totalPrize / money * 100;

    return Math.round(profitRate * 10) / 10.0;
  }
}
