//package thread;
//
//import construct.Customer;
//import construct.Window;
//
//import java.util.ArrayList;
//import java.util.Queue;
//
///**
// * @author ghy
// * @date 2020/11/18 下午7:33
// */
//public class CustomerComing implements Runnable {
//    private int customerSize;
//    private ArrayList<Customer> customerList;
//    private ArrayList<Window> windowsList;
//
//    public CustomerComing(int customerSize, ArrayList<Customer> customerList, ArrayList<Window> windowsList) {
//        this.customerSize = customerSize;
//        this.customerList = customerList;
//        this.windowsList = windowsList;
//    }
//
//    public void findWindow() {
//
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < this.customerSize; i++) {
//            try {
//                System.out.println("Customer Coming");
//                findWindow();
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
