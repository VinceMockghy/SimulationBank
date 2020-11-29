package construct;

/**
 * 日志类，记录一些银行顾客到来，服务内容，服务时间等基本信息
 * @author ghy
 * @date 2020/11/22 下午11:00
 */
public class Content {
    private String customerName;
    private int priority;
    private long arriveTime;
    private long finishTime;
    private long waitTime;
    private Business businessType;
    private long useTime;
    private Window serveWindow;

    public Content(String customerName, int priority, long arriveTime,long finishTime,long waitTime, Business businessType, long useTime, Window serveWindow) {
        this.customerName = customerName;
        this.priority = priority;
        this.arriveTime = arriveTime;
        this.finishTime = finishTime;
        this.waitTime = waitTime;
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

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

}
