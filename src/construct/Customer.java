package construct;

/**
 * @author ghy
 * @date 2020/11/18 下午4:39
 */
public class Customer {
    private int priority;
    private int id;
    private Business business;

    public Customer(int id, int priority){
        this.id = id;
        this.priority = priority;
//        this.business = Business.
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
