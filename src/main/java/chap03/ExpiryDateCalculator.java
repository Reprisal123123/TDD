package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
        return billingDate.plusMonths(1);
    }

    public LocalDate calculateExpiryDate(PayData payData) {
        int payAmount = payData.getPayAmount();
        int addedMonths =
                payAmount >= 100_000 ?
                        (payAmount / 100_000) * 12 + (payAmount % 100_000) / 10_000  : payData.getPayAmount() / 10_000;

        // 첫 납부일자가 있으면
        if(payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);

        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }

    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        // 후보 만료일 구함
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        // 첫 납부일자
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

        // 첫 납부일의 일자와 후보 만료일의 일자가 다르면
        if(dayOfFirstBilling != candidateExp.getDayOfMonth()) {

            // 후보 만료일이 포함된 달의 마지막날이 첫 납부일의 일자보다 작으면
            // 후보 만료일을 그 달의 마지막 날로 설정
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();

            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }

            // 첫 납부일의 일자를 후보 만료일의 일자로 사용
            return candidateExp.withDayOfMonth(dayOfFirstBilling);

        } else {
            return candidateExp;
        }
    }
}
