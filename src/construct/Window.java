package construct;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ghy
 * @date 2020/11/18 下午4:29
 */
public enum Window {
    /**
     * A类窗口 业务类型1,2,3,4,5,6,7,8 服务对象所有人
     */
    A(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8)),1),
    /**
     * B类窗口 业务类型1,2,4,5,7 服务对象所有人
     */
    B(new ArrayList<Integer>(Arrays.asList(1,2,4,5,7)),1),
    /**
     * V类窗口 业务类型1,2,3,4,5,6,7,8 服务对象VIP优先
     */
    V(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8)),0);

    protected ArrayList<Integer> serviceType;
    protected int priority;

    private Window(ArrayList<Integer> serviceType, int priority){
        this.serviceType = serviceType;
        this.priority = priority;
    }

    public ArrayList<Integer> getServiceType() {
        return serviceType;
    }

    public void setServiceType(ArrayList<Integer> serviceType) {
        this.serviceType = serviceType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
