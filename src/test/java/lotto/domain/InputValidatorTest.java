package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

  private InputValidator validator;

  @BeforeEach
  void setUp() {
    validator = new InputValidator();
  }

  @DisplayName("올바른 금액은 정상 처리된다.")
  @Test
  void 올바른_금액은_정상_처리된다() {
    assertThat(validator.validateMoney("5000")).isEqualTo(5000);
  }

  @DisplayName("숫자가 아닌 금액 입력 시 예외가 발생한다.")
  @Test
  void 숫자가_아닌_금액_입력시_예외가_발생한다() {
    assertThatThrownBy(() -> validator.validateMoney("abc"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("금액이_000원 단위가 아니면 예외가 발생한다.")
  @Test
  void 금액이_1000원_단위가_아니면_예외가_발생한다() {
    assertThatThrownBy(() -> validator.validateMoney("1500"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("최대 금액 이상이면 예외가 발생한다.")
  @Test
  void 최대_금액_이상이면_예외가_발생한다() {
    assertThatThrownBy(() -> validator.validateMoney("100000"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("올바른 당첨 번호 형식은 정상 처리된다.")
  @Test
  void 올바른_당첨_번호_형식은_정상_처리된다() {
    assertThatNoException()
            .isThrownBy(() -> validator.validateWinningNumber("1,2,3,4,5,6"));
  }

  @DisplayName("숫자와 쉼표 외 문자가 있으면 예외가 발생한다.")
  @Test
  void 숫자와_쉼표_외_문자가_있으면_예외가_발생한다() {
    assertThatThrownBy(() -> validator.validateWinningNumber("1,2,3,a,5,6"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("올바른 보너스 번호는 정상 처리된다.")
  @Test
  void 올바른_보너스_번호는_정상_처리된다() {
    assertThatNoException()
            .isThrownBy(() -> validator.validateBonusNumber("7"));
  }

  @DisplayName("보너스 번호가 1 미만이면 예외가 발생한다.")
  @Test
  void 보너스_번호가_1_미만이면_예외가_발생한다() {
    assertThatThrownBy(() -> validator.validateBonusNumber("0"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("보너스 번호가 45 초과이면 예외가 발생한다.")
  @Test
  void 보너스_번호가_45_초과이면_예외가_발생한다() {
    assertThatThrownBy(() -> validator.validateBonusNumber("46"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
  @Test
  void 보너스_번호가_숫자가_아니면_예외가_발생한다() {
    assertThatThrownBy(() -> validator.validateBonusNumber("abc"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
  }

  @DisplayName("쉼표로 구분된 문자열을 숫자 리스트로 변환한다.")
  @Test
  void 쉼표로_구분된_문자열을_숫자_리스트로_변환한다() {
    assertThat(validator.parseNumbers("1,2,3,4,5,6"))
            .containsExactly(1, 2, 3, 4, 5, 6);
  }

  @DisplayName("공백이 포함된 입력도 정상 파싱된다.")
  @Test
  void 공백이_포함된_입력도_정상_파싱된다() {
    assertThat(validator.parseNumbers("1, 2, 3, 4, 5, 6"))
            .containsExactly(1, 2, 3, 4, 5, 6);
  }
}
