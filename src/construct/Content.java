package construct;

/**
 * @author ghy
 * @date 2020/11/22 下午11:00
 */
public class Content {
    private String customerName;
    private int priority;
    private long arriveTime;
    private Business businessType;
    private long useTime;
    private Window serveWindow;

    public Content(String customerName, int priority, long arriveTime, Business businessType, long useTime, Window serveWindow) {
        this.customerName = customerName;
        this.priority = priority;
        this.arriveTime = arriveTime;
        this.businessType = businessType;
        this.useTime = useTime;
        this.serveWindow = serveWindow;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Window getServeWindow() {
        return serveWindow;
    }

    public void setServeWindow(Window serveWindow) {
        this.serveWindow = serveWindow;
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
