package construct;

/**
 * @author ghy
 * @date 2020/11/22 下午11:00
 */
public class Content {
    private String customerName;
    private long arriveTime;
    private Business businessType;
    private long useTime;

    public Content(String customerName, long arriveTime, Business businessType, long useTime) {
        this.customerName = customerName;
        this.arriveTime = arriveTime;
        this.businessType = businessType;
        this.useTime = useTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Business getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Business businessType) {
        this.businessType = businessType;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    @Override
    public String toString() {
        return "Content{" +
                "customerName='" + customerName + '\'' +
                ", arriveTime=" + arriveTime +
                ", businessType=" + businessType +
                ", useTime=" + useTime +
                '}';
    }
}
