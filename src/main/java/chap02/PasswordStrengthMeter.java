package chap02;

import org.junit.platform.commons.util.StringUtils;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        // 비밀번호가 null이거나 빈 문자열이면 INVALID를 반환
        if(StringUtils.isBlank(password)) return PasswordStrength.INVALID;

        boolean lengthEnough = (password.length() >= 8);

        boolean containsNum = meetsContainingNumberCriteria(password);

        boolean containsUppercase = meetsContainingUppercaseCriteria(password);

        // 길이 조건만 만족하는 경우
        if(lengthEnough && !containsNum && !containsUppercase) {
            return PasswordStrength.WEAK;
        }

        if(!lengthEnough && containsNum && !containsUppercase) {
            return PasswordStrength.WEAK;
        }

        if(!lengthEnough && !containsNum && containsUppercase) {
            return PasswordStrength.WEAK;
        }

        if(!lengthEnough) return PasswordStrength.NORMAL;
        if(!containsNum) return PasswordStrength.NORMAL;
        if(!containsUppercase) return PasswordStrength.NORMAL;

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
