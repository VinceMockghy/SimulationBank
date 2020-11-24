package construct;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ghy
 * @date 2020/11/24 下午7:09
 */
public class TimeChange {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static long rate = 60*1000;
    private long openTime;
    private Calendar calendar = Calendar.getInstance();

    public TimeChange(long openTime) {
        this.openTime = openTime;
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(sdf.format(calendar.getTime()));

    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public String timeMap(long mapTime) {
        long subtract = mapTime - openTime;
        long resultTime = calendar.getTimeInMillis()+subtract*rate;
        return sdf.format(new Date(resultTime));
    }
}
