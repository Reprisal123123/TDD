package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        // 길이가 8보다 짧으면
        if(password.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        boolean containsNum = meetsContainingNumberCriteria(password);

        // 숫자를 포함하지 않으면
        if(!containsNum) return PasswordStrength.NORMAL;

        // 그 외는 모두 STRONG
        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String password) {
        for(char ch : password.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
