package chap02;

import org.junit.platform.commons.util.StringUtils;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        // 비밀번호가 null이거나 빈 문자열이면
        if(StringUtils.isBlank(password)) return PasswordStrength.INVALID;

        // 길이가 8보다 짧으면
        if(password.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        // 숫자를 포함하지 않는 경우
        boolean containsNum = meetsContainingNumberCriteria(password);

        if(!containsNum) return PasswordStrength.NORMAL;

        // 대문자를 포함하지 않는 경우
        boolean containsUppercase = meetsContainingUppercaseCriteria(password);

        if(!containsUppercase) {
            return PasswordStrength.NORMAL;
        }

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
}
