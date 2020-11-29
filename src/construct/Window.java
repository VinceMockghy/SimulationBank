package construct;

import java.util.ArrayList;

/**
 * 窗口类
 * A类窗口 业务类型1,2,3,4,5,6,7,8 服务对象所有人
 * B类窗口 业务类型1,2,4,5,7 服务对象所有人
 * V类窗口 业务类型1,2,3,4,5,6,7,8 服务对象VIP优先
 * @author ghy
 * @date 2020/11/18 下午4:29
 */
public class Window {

    private int id;
    private String name;
    private ArrayList<Integer> serviceType;
    private int priority;

    public Window(int id,String name, ArrayList<Integer> serviceType, int priority) {
        this.id = id;
        this.name = name;
        this.serviceType = serviceType;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

