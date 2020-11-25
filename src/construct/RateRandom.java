package construct;

/**
 * @author ghy
 * @date 2020/11/25 下午4:09
 */
public class RateRandom {
    private double rate1 = 0.125;
    private double rate2 = 0.125;
    private double rate3 = 0.125;
    private double rate4 = 0.125;
    private double rate5 = 0.125;
    private double rate6 = 0.125;
    private double rate7 = 0.125;
    private double rate8 = 0.125;

    public RateRandom(double rate1, double rate2, double rate3, double rate4, double rate5, double rate6, double rate7, double rate8) {
        this.rate1 = rate1;
        this.rate2 = rate2;
        this.rate3 = rate3;
        this.rate4 = rate4;
        this.rate5 = rate5;
        this.rate6 = rate6;
        this.rate7 = rate7;
        this.rate8 = rate8;
    }

    public RateRandom(){

    }
    public Business getRandomBusiness(){
        double randomNumber;
        randomNumber = Math.random();
        if(randomNumber>0 && randomNumber<=rate1){
            return Business.DEPOSIT;
        }
        else if(randomNumber>rate1 && randomNumber<=rate1+rate2){
            return Business.WITH_DRAWAL;
        }else if(randomNumber>rate1+rate2 && randomNumber<=rate1+rate2+rate3){
            return Business.PAY_FINE;
        }else if(randomNumber>rate1+rate2+rate3 && randomNumber<=rate1+rate2+rate3+rate4){
            return Business.OPEN_ONLINE_BANKING;
        }else if(randomNumber>rate1+rate2+rate3+rate4 && randomNumber<=rate1+rate2+rate3+rate4+rate5){
            return Business.PAY_WATER_AND_ELECTRICITY_BILLS;
        }else if(randomNumber>rate1+rate2+rate3+rate4+rate5 && randomNumber<=rate1+rate2+rate3+rate4+rate5+rate6){
            return Business.PURCHASE_FUND;
        }else if(randomNumber>rate1+rate2+rate3+rate4+rate5+rate6 && randomNumber<=rate1+rate2+rate3+rate4+rate5+rate6+rate7){
            return Business.TRANSFER_REMITTANCE;
        }
        else {
            return Business.INDIVIDUAL_LOAN_REPAYMENT;
        }
//        else if(randomNumber>rate1+rate2+rate3+rate4+rate5+rate6+rate7 && randomNumber<=rate1+rate2+rate3+rate4+rate5+rate6+rate7+rate8){
//            return Business.INDIVIDUAL_LOAN_REPAYMENT;
//        }
    }
}
