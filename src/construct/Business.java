package construct;

import java.util.Random;

/**
 * @author ghy
 * @date 2020/11/17 下午4:43
 */
public enum Business {
    /**
     * 存款
     */
    DEPOSIT(1,0.5,1.5),
    /**
     * 取款
     */
    WITH_DRAWAL(2,0.5,1.5),
    /**
     * 缴纳罚款
     */
    PAY_FINE(3,1.2,2),
    /**
     * 开通网银
     */
    OPEN_ONLINE_BANKING(4,5,8),
    /**
     * 交水电费
     */
    PAY_WATER_AND_ELECTRICITY_BILLS(5,1.5,2),
    /**
     * 购买基金
     */
    PURCHASE_FUND(6,2,3),
    /**
     * 转账汇款
     */
    TRANSFER_REMITTANCE(7,3,4),
    /**
     * 个贷还款
     */
    INDIVIDUAL_LOAN_REPAYMENT(8,2,4);

    protected int id;
    protected double lowTimeProportion;
    protected double highTimeProportion;

    private Business(int id, double lowTimeProportion, double highTimeProportion){
        this.id = id;
        this.lowTimeProportion=lowTimeProportion;
        this.highTimeProportion = highTimeProportion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLowTimeProportion() {
        return lowTimeProportion;
    }

    public void setLowTimeProportion(double lowTimeProportion) {
        this.lowTimeProportion = lowTimeProportion;
    }

    public double getHighTimeProportion() {
        return highTimeProportion;
    }

    public void setHighTimeProportion(double highTimeProportion) {
        this.highTimeProportion = highTimeProportion;
    }

    public static Business getRandomBusiness(){
        int pick = new Random().nextInt(Business.values().length);
        return Business.values()[pick];
    }
}
