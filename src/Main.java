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
    static double baseTime = 1000;
    static int flag = -1;

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
        System.out.println("基准时间为1秒");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.execute(new Test(customerQueue));
        executorService.execute(new CustomerComing(10, preCustomer, windowsList));
        for (int i = 0; i < 4; i++) {
            executorService.execute(new WindowServing(windowsList.get(i),baseTime));
        }
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
        Main.flag = 1;
    }
}

class WindowServing implements Runnable{
    Window serveWindow;
    double baseTime;
    WindowServing(Window serveWindow,double baseTime){
        this.serveWindow = serveWindow;
        this.baseTime = baseTime;
    }

    @Override
    public void run() {
        System.out.println("窗口"+this.serveWindow.getName()+"窗口id"+this.serveWindow.getId()+"开启");
        while (true){
            if(serveWindow.getServiceQueue().isEmpty() ){
                if(Main.flag==1){
                    break;
                }
                continue;
            }else {
                try {
                    System.out.println("窗口名"+this.serveWindow.getName()+" 窗口id"+this.serveWindow.getId()+" 服务顾客");
                    Customer customer = serveWindow.getServiceQueue().poll();
                    assert customer != null;
                    double low = customer.getBusiness().getLowTimeProportion();
                    double high = customer.getBusiness().getHighTimeProportion();
                    int serveTime = new Random().nextInt((int) (baseTime*(high-low)))+(int)(baseTime*low);
                    Thread.sleep(serveTime);
                    System.out.println("顾客"+customer.getId()+"在窗口"+this.serveWindow.getName()+"完成服务,"+"耗时:"+serveTime*1.0/1000+"秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("窗口"+this.serveWindow.getName()+"窗口id"+this.serveWindow.getId()+"关闭");
    }
}