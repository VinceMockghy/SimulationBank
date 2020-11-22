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
