package construct;

import java.util.Random;

/**
 * 枚举类，各种银行的业务类型，包含id，最小基本时间倍率，最大基本时间倍率，类型名称
 * @author ghy
 * @date 2020/11/17 下午4:43
 */
public enum Business {
    /**
     * 存款
     */
    DEPOSIT(1,0.5,1.5,"存款"),
    /**
     * 取款
     */
    WITH_DRAWAL(2,0.5,1.5,"取款"),
    /**
     * 缴纳罚款
     */
    PAY_FINE(3,1.2,2,"缴纳罚款"),
    /**
     * 开通网银
     */
    OPEN_ONLINE_BANKING(4,5,8,"开通网银"),
    /**
     * 交水电费
     */
    PAY_WATER_AND_ELECTRICITY_BILLS(5,1.5,2,"交水电费"),
    /**
     * 购买基金
     */
    PURCHASE_FUND(6,2,3,"购买基金"),
    /**
     * 转账汇款
     */
    TRANSFER_REMITTANCE(7,3,4,"转账汇款"),
    /**
     * 个贷还款
     */
    INDIVIDUAL_LOAN_REPAYMENT(8,2,4,"个贷还款");

    protected int id;
    protected double lowTimeProportion;
    protected double highTimeProportion;
    protected String name;

    private Business(int id, double lowTimeProportion, double highTimeProportion,String name){
        this.id = id;
        this.lowTimeProportion=lowTimeProportion;
        this.highTimeProportion = highTimeProportion;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Business getRandomBusiness(){
        int pick = new Random().nextInt(Business.values().length);
        return Business.values()[pick];
    }
}
