import construct.Business;
import construct.Content;
import construct.Customer;
import construct.Window;
import thread.Bank;

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
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.bankOpen();

    }
}