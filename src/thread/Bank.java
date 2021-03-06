package thread;

import construct.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ghy
 * @date 2020/11/23 上午11:07
 */
public class Bank {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static ArrayList<Customer> globalCustomQueue = new ArrayList<Customer>();
    static ArrayList<Customer> preCustomer = new ArrayList<>();
    static ArrayList<Window> windowsList = new ArrayList<>();
    /**
     * baseTime会映射为真实的5分钟，也就是1ms对应1分钟
     */
    static double baseTime = 5;
    static double vipRate = 0.1;
    static double totalCustom = 150;
    static int flag = -1;
    static ArrayList<Content> contents = new ArrayList<>();
    static HashMap<Business, Integer> businessCountMap = new HashMap<>();
    static long openTime;
    static long closeTime;
    static long needWorkTime = 540;
    static TimeChange timeChange;
    static RateRandom rateRandom;

    public Bank(double baseTime) {
        Bank.baseTime = baseTime;
    }

    public Bank() {

    }

    /**
     * 按照比例创建所有的一般用户以及Vip用户，添加到预先数组preCustomer中，最后用shuffle进行顺序打乱
     */
    public void makeCustomer() {
        for (int i = 0; i < (int)(totalCustom*(1-vipRate)); i++) {
            preCustomer.add(new Customer(i, "顾客" + i, 0,rateRandom.getRandomBusiness()));
        }
        for (int i = (int)(totalCustom*(1-vipRate)); i < totalCustom; i++) {
            preCustomer.add(new Customer(i, "顾客" + i, 1,rateRandom.getRandomBusiness()));
        }
        Collections.shuffle(preCustomer);
    }

    /**
     * 创建银行窗口，创建四个窗口 V A B B
     */
    public void makeWindow() {
        windowsList.add(new Window(0, "V", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))));
        windowsList.add(new Window(1, "A", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))));
//        windowsList.add(new Window(2, "A", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))));
        windowsList.add(new Window(2, "B", new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 7))));
        windowsList.add(new Window(3, "B", new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 7))));
    }

    /**
     * 输出客户的平均办理时间
     * 从日志中把所有顾客的完成时间减去到达时间，最后除以日志数量即可
     */
    public void outputCustomAvgServeTime(){
        long sum=0;
        for (Content content : contents) {
            sum = sum + (content.getFinishTime() - content.getArriveTime());
        }
        System.out.printf("平均办理时间：%.2fmin\n",sum*1.0/contents.size());
    }

    /**
     * 输出办理每个业务的比例
     */
    public void outputRateOfBusiness() {
        System.out.println("不同业务在所有办理业务中所占的比例为：");
        for (Business b : Business.values()) {
            try {
                double rate = Bank.businessCountMap.get(b) * 1.0 / Bank.contents.size() * 100;
                System.out.printf("%s:%.3f%%\n", b.getName(), rate);
            } catch (NullPointerException e) {
                System.out.printf("%s:%.3f%%\n", b.getName(), 0.0);
            }
        }
    }

    /**
     * 银行开门
     */
    public void bankOpen() {
        System.out.println("银行开门");
        // 正常日
        rateRandom = new RateRandom();
        // 基准日A
//        rateRandom = new RateRandom(0.2,0.2,0.1,0.1,0.05,0.15,0.10,0.10);
        // 营业日B
//        rateRandom = new RateRandom(0.1,0.1,0.05,0.05,0.05,0.40,0.05,0.20);
        System.out.println("working.....");
        openTime = System.currentTimeMillis();
        timeChange = new TimeChange(openTime);
        this.makeCustomer();
        this.makeWindow();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new CustomerComing(preCustomer.size(), preCustomer, globalCustomQueue));
        for (int i = 0; i < 4; i++) {
            executorService.execute(new WindowServing(windowsList.get(i), baseTime, globalCustomQueue));
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                System.out.println("银行关门啦，汇报进度");
                System.out.println("一天的顾客日志如下");
                System.out.println("一共服务了：" + contents.size() + "名顾客");
                System.out.printf("%-8s %-10s %-15s %-12s %-15s %-12s %-15s %-10s\n",
                        "客户名称", "是否为vip", "客户到达时间", "客户办理业务类型", "客户所需时间(分钟)", "服务窗口","顾客完成时间","顾客等待时间");
                for (Content c : contents) {
                    System.out.printf("%-10s %-9s %-22s %-17s %-18s %-12s %-22s %-10s\n",
                            c.getCustomerName(), c.getPriority(), timeChange.timeMap(c.getArriveTime()),
                            c.getBusinessType().getName(), c.getUseTime(),
                            c.getServeWindow().getName() + c.getServeWindow().getId(),
                            timeChange.timeMap(c.getFinishTime()),c.getWaitTime());
                }
                outputCustomAvgServeTime();
                outputRateOfBusiness();
                break;
            }
        }
        System.out.println("汇报完毕，正式关门");
    }

    /**
     * 顾客到来线程类
     * run 方法中在随机等待两倍的基准时间后从preCustomer列表中获取一个客户后放置到银行等待队列中
     */
    static class CustomerComing implements Runnable {
        private int customerSize;
        private ArrayList<Customer> customerList;
        private final ArrayList<Customer> globalCustomQueue;

        public CustomerComing(int customerSize, ArrayList<Customer> customerList, ArrayList<Customer> globalCustomQueue) {
            this.customerSize = customerSize;
            this.customerList = customerList;
            this.globalCustomQueue = globalCustomQueue;
        }
        @Override
        public void run() {
            for (int i = 0; i < this.customerSize; i++) {
                try {
                    closeTime = System.currentTimeMillis();
                    if (closeTime - openTime >= needWorkTime) {
                        synchronized (globalCustomQueue){
                            System.out.println("排队的还有"+globalCustomQueue.size()+"位客人");
                        }
                        break;
                    }
                    System.out.println(customerList.get(i).getName() + "到达,取号等待,需要办理的业务类型为："
                            + customerList.get(i).getBusiness().getId());
                    customerList.get(i).setArriveTime(System.currentTimeMillis());
                    synchronized (globalCustomQueue) {
                        globalCustomQueue.add(customerList.get(i));
                    }
                    Thread.sleep(new Random().nextInt((int) Bank.baseTime * 2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Bank.flag = 1;
        }
    }

    /**
     * 窗口服务线程类
     * run 方法中四个窗口不断的从银行的等待队列中获取顾客，使用同步锁做到线程之间的控制
     */
    static class WindowServing implements Runnable {
        private final Window serveWindow;
        private final double baseTime;
        private final ArrayList<Customer> globalCustomQueue;

        WindowServing(Window serveWindow, double baseTime, ArrayList<Customer> globalCustomQueue) {
            this.serveWindow = serveWindow;
            this.baseTime = baseTime;
            this.globalCustomQueue = globalCustomQueue;
        }

        /**
         * 从银行的顾客等待队列获取一名顾客进行服务
         * 如果银行的窗口名称为A类窗口，就直接从顾客等待队列里拿第一个数据并移除
         * 如果银行的窗口名称为B类窗口，遍历每一个等待队列中的顾客，如果某个顾客办理的业务B窗口无法执行就跳过这个顾客
         * 如果银行的窗口名称为V类窗口，遍历每一个等待队列中的顾客，如果顾客是VIP(priority = 0)，就优先返回这个顾客并移除队列，如果没有直接取第一个
         *
         * @return Customer
         */
        public Customer getCustomerFromQueue() {
            synchronized (globalCustomQueue) {
                if (!readQueue()) {
                    if ("A".equals(this.serveWindow.getName())) {
                        Customer res = globalCustomQueue.get(0);
                        globalCustomQueue.remove(0);
                        return res;
                    } else if ("B".equals(this.serveWindow.getName())) {
                        for (int i = 0; i < globalCustomQueue.size(); i++) {
                            Business customer = globalCustomQueue.get(i).getBusiness();
                            if (customer == Business.PAY_FINE || customer == Business.PURCHASE_FUND
                                    || customer == Business.INDIVIDUAL_LOAN_REPAYMENT) {
                                continue;
                            } else {
                                Customer res = globalCustomQueue.get(i);
                                globalCustomQueue.remove(i);
                                return res;
                            }
                        }
                        return null;
                    } else if ("V".equals(this.serveWindow.getName())) {
                        for (int i = 0; i < globalCustomQueue.size(); i++) {
                            if (globalCustomQueue.get(i).getPriority() == 1) {
                                Customer res = globalCustomQueue.get(i);
                                globalCustomQueue.remove(i);
                                return res;
                            }
                        }
                        Customer res = globalCustomQueue.get(0);
                        globalCustomQueue.remove(0);
                        return res;
                    }
                } else {
                    return null;
                }
            }
            return null;
        }


        /**
         * 获取队列当前状态，是否为空
         *
         * @return Boolean
         */
        public Boolean readQueue() {
            synchronized (globalCustomQueue) {
                return globalCustomQueue.isEmpty();
            }
        }

        @Override
        public void run() {
            System.out.println("窗口" + this.serveWindow.getName() + "窗口id" + this.serveWindow.getId() + "开启");
            while (true) {
                if (readQueue()) {
                    if (Bank.flag == 1) {
                        break;
                    }
                } else {
                    Customer customer = getCustomerFromQueue();
                    if (customer == null) {
                        continue;
                    }
                    try {
                        System.out.println("窗口"+this.serveWindow.getName()+"窗口id"+this.serveWindow.getId()+
                                " 服务顾客"+customer.getName());
                        double low = customer.getBusiness().getLowTimeProportion();
                        double high = customer.getBusiness().getHighTimeProportion();
                        int serveTime = new Random().nextInt((int) (baseTime * (high - low))) + (int) (baseTime * low);
                        Thread.sleep(serveTime);
                        customer.setFinishTime(System.currentTimeMillis());
                        customer.setWaitTime(customer.getFinishTime()-customer.getArriveTime()-serveTime);
                        synchronized (Bank.contents) {
                            Bank.contents.add(new Content(customer.getName(), customer.getPriority(), customer.getArriveTime(),customer.getFinishTime(),customer.getWaitTime(), customer.getBusiness(), serveTime, this.serveWindow));
                            if (Bank.businessCountMap.get(customer.getBusiness()) == null) {
                                Bank.businessCountMap.put(customer.getBusiness(), 1);
                            } else {
                                int cnt = Bank.businessCountMap.get(customer.getBusiness());
                                Bank.businessCountMap.put(customer.getBusiness(), cnt + 1);
                            }
                        }
                        System.out.println(customer.getName()+"在窗口"+this.serveWindow.getName()+"完成服务,"+"耗时:"+serveTime*1.0/1000+"秒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Thread.yield();
            }
            System.out.println("窗口" + this.serveWindow.getName() + "窗口id" + this.serveWindow.getId() + "关闭");
        }
    }
}
