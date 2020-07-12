package wmaclean.time;
import java.time.LocalTime;

public class Clock {

    private static final  LocalTime[] changeTimes = new LocalTime[]{
            LocalTime.parse("05:00"),  // start sunrise
            LocalTime.parse("08:00"),  // start day
            LocalTime.parse("17:00"), // start sunset
            LocalTime.parse("20:00")  // start night
    };

    public static LocalTime getTime(){
        return LocalTime.now();
    }

    public static TimeOfDay getTimeOfDay(){
        LocalTime now = getTime();

        if(now.isBefore(changeTimes[0])){
            return TimeOfDay.Dark;
        }else if(now.isBefore(changeTimes[1])){
            return TimeOfDay.DuskDawn;
        }else if(now.isBefore(changeTimes[2])){
            return TimeOfDay.Sunny;
        }else if(now.isBefore(changeTimes[3])){
            return TimeOfDay.DuskDawn;
        }else {
            return TimeOfDay.Moonlight;
        }
    }
}
