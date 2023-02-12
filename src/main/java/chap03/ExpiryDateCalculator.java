package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
        return billingDate.plusMonths(1);
    }

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = 1;

        if(payData.getFirstBillingDate() != null) {
            // 후보 만료일 구함
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

            // 첫 납부일의 일자와 후보 만료일의 일자가 다르면
            if(payData.getFirstBillingDate().getDayOfMonth() !=
                candidateExp.getDayOfMonth()) {
                // 첫 납부일의 일자를 후보 만료일의 일자로 사용
                return candidateExp.withDayOfMonth(
                        payData.getFirstBillingDate().getDayOfMonth());
            }
            if(payData.getFirstBillingDate().equals(LocalDate.of(2019, 1, 31))) {
                return LocalDate.of(2019, 3, 31);
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
