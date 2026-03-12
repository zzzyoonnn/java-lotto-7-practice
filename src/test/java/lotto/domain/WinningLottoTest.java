package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

  @DisplayName("올바른 당첨 번호와 보너스 번호로 생성할 수 있다.")
  @Test
  void 정상적인_WinningLotto_생성() {
    assertThatNoException()
            .isThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7));
  }

  @DisplayName("당첨 번호가 6개가 넘으면 예외가 발생한다.")
  @Test
  void 당첨_번호가_6개_초과면_예외가_발생한다() {
    assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6, 7), 8))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("당첨 번호가 6개 미만이면 예외가 발생한다.")
  @Test
  void 당첨_번호가_6개_미만이면_예외가_발생한다() {
    assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5), 6))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
  @Test
  void 당첨_번호에_중복이_있으면_예외가_발생한다() {
    assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 5), 7))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
  @Test
  void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
    assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("당첨 번호를 정상적으로 반환한다.")
  @Test
  void 당첨_번호를_반환한다() {
    WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

    assertThat(winningLotto.getWinningNumbers())
            .containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
  }

  @DisplayName("보너스 번호를 정상적으로 반환한다.")
  @Test
  void 보너스_번호를_반환한다() {
    WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

    assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
  }
}
