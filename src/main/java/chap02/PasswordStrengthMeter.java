package chap02;

import org.junit.platform.commons.util.StringUtils;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        // 비밀번호가 null이거나 빈 문자열이면 INVALID를 반환 - 예외상황을 먼저 테스트하기
        if(StringUtils.isBlank(password)) return PasswordStrength.INVALID;

        int metCounts = getMetCriteriaCounts(password);

        // 1가지 조건만 만족하는 경우 WEAK 반환
        if(metCounts <= 1) return PasswordStrength.WEAK;

        // 2가지 조건만 만족하는 경우 NORMAL 반환
        if(metCounts == 2) return PasswordStrength.NORMAL;

        // 그 외는 모두 STRONG
        return PasswordStrength.STRONG;
    }

    // 숫자를 포함하고 있는지 검사하는 메서드
    private boolean meetsContainingNumberCriteria(String password) {
        for(char ch : password.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    // 대문자를 포함하고 있는지 검사하는 메서드
    private boolean meetsContainingUppercaseCriteria(String password) {
        for(char ch : password.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private int getMetCriteriaCounts(String password) {
        int metCounts = 0;

        if(password.length() >= 8) metCounts++;

        if(meetsContainingNumberCriteria(password)) metCounts++;

        if(meetsContainingUppercaseCriteria(password)) metCounts++;

        return metCounts;
    }
}
