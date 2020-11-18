package thread;

import construct.Customer;

import java.util.Queue;

/**
 * @author ghy
 * @date 2020/11/18 下午8:07
 */
public class Test implements Runnable {
    private Queue<Customer> customerQueue;

    public Test(Queue<Customer> customerQueue){
        this.customerQueue = customerQueue;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(300);
                System.out.println(customerQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
