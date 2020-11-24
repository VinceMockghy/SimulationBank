package thread;

import construct.Customer;
import construct.TimeChange;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Queue;

/**
 * @author ghy
 * @date 2020/11/18 下午8:07
 */
public class Test{
    public static void main(String[] args) throws InterruptedException {
//        Timestamp date = (Timestamp) new Date();
//        System.out.println(System.currentTimeMillis());
//        Thread.sleep(1000);
//        System.out.println(System.currentTimeMillis());
        TimeChange timeChange = new TimeChange(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        Thread.sleep(540);
        System.out.println(System.currentTimeMillis());
        System.out.println(timeChange.timeMap(System.currentTimeMillis()));

    }
}
