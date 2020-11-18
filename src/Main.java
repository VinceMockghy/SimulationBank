import construct.Business;
import construct.Customer;
import construct.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ghy
 * @date 2020/11/18 下午4:25
 */
public class Main {
    static ArrayList<Customer> preCustomer = new ArrayList<>();
    static ArrayList<Window> windowsList = new ArrayList<>();

    public static void makeCustomer() {
        for (int i = 0; i < 10; i++) {
            preCustomer.add(new Customer(i, 1));
        }
        Collections.shuffle(preCustomer);
    }

    public static void makeWindow() {
        windowsList.add(new Window(0, "V", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)), 0));
        windowsList.add(new Window(1, "A", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)), 1));
        windowsList.add(new Window(2, "B", new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 7)), 1));
        windowsList.add(new Window(3, "B", new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 7)), 1));
    }

    public static void main(String[] args) {
        makeCustomer();
        makeWindow();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.execute(new Test(customerQueue));
        executorService.execute(new CustomerComing(10, preCustomer, windowsList));

        executorService.shutdown();

    }
}


class CustomerComing implements Runnable {
    private int customerSize;
    private ArrayList<Customer> customerList;
    private ArrayList<Window> windowsList;

    public CustomerComing(int customerSize, ArrayList<Customer> customerList, ArrayList<Window> windowsList) {
        this.customerSize = customerSize;
        this.customerList = customerList;
        this.windowsList = windowsList;
    }

    public void findWindow(Customer customer) {
        if (customer.getBusiness() == Business.PAY_FINE ||
                customer.getBusiness() == Business.PURCHASE_FUND ||
                customer.getBusiness() == Business.INDIVIDUAL_LOAN_REPAYMENT) {
            windowsList.get(new Random().nextInt(2)+2).getServiceQueue().add(customer);
        }
        else {
            windowsList.get(new Random().nextInt(2)).getServiceQueue().add(customer);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < this.customerSize; i++) {
            try {
                System.out.println("Customer Coming");
                findWindow(customerList.get(i));
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class WindowServing implements Runnable{
    Window serveWindow;
    WindowServing(Window serveWindow){
        this.serveWindow = serveWindow;
    }

    @Override
    public void run() {

    }
}