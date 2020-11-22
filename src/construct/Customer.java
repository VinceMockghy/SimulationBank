package construct;

/**
 * @author ghy
 * @date 2020/11/18 下午4:39
 */
public class Customer {
    private int priority;
    private int id;
    private String name;
    private Business business;

    public Customer(int id, String name,int priority) {
        this.priority = priority;
        this.id = id;
        this.name = name;
        this.business = Business.getRandomBusiness();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Business getBusiness() {
        return business;
    }

    public int getServiceTime(){
        return 0;
    }
}
