/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Edward Galindez
 * @version 03/13/2022
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses from a given log file
     * @param logFile The name of the log file.
     * @param logSize The size of the log file entries
     */
    public LogAnalyzer(String logFile)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(logFile);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Returns the busiest hour recorded
     * @return busyHour The busiest hour recorded
     */
    public int busiestHour()
    {
        int hourCount = 0;
        int busyHour = 0;
        for(int hours = 0; hours < hourCounts.length; hours++)
        {
            if(hourCounts[hours] > hourCount)
            {
                hourCount = hourCounts[hours];
                busyHour = hours;
            }
        }
        return busyHour;
    }
    
    /**
     * Returns the quietest hour recorded
     * @return quietHour The quiestest hour recorded
     */
    public int quietestHour()
    {
        int hourCount = hourCounts[0];
        int quietHour = 0;
        for(int hours = 0; hours < hourCounts.length; hours++)
        {
            if(hourCounts[hours] < hourCount)
            {
                hourCount = hourCounts[hours];
                quietHour = hours;
            }
        }
        return quietHour;
    }
    
    /**
     * Return the number of accesses recorded in the log file
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int hours = 0; hours < hourCounts.length ;hours++)
        {
            total += hourCounts[hours];
        }
        return total;
    }
    
    /**
     * Returns the busiest two hours recorded in the log file
     * @return startingBusyHour The starting hour of the busiest two hou
     */
    public int busiestTwoHours()
    {
        int largestSum = 0;
        int hourCountSum = 0;
        int startingBusyHour = 0;
        for(int hours = 0; hours < hourCounts.length; hours++)
        {
            if(hours < hourCounts.length - 2)
            {
                int hour1 = hourCounts[hours];
                int hour2 = hourCounts[hours + 1];
                int hour3 = hourCounts[hours + 2];
                if(hour1 + hour2 > hour2 + hour3)
                {
                    hourCountSum = hour1 + hour2;
                }
                else
                {
                    hourCountSum = hour2 + hour3;
                }
            }
            else if (hours == hourCounts.length - 2)
            {
                hourCountSum = hourCounts[hourCounts.length - 1] + hourCounts[hourCounts.length - 2];
            }
            if(hourCountSum >= largestSum)
            {
                largestSum = hourCountSum;
                startingBusyHour = hours;
            } 
        }
        return startingBusyHour;
    }
    
    /**
     * Returns the busiest day recorded in the log file
     * @return busyDay The busiest day of the year
     */
    public int busiestDay()
    {
        int[] dayCounts = new int[28];
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay() - 1;
            dayCounts[day]++;
        }
        
        int dayCount = 0;
        int busyDay = 0;
        for(int days = 0; days < dayCounts.length; days++)
        {
            if(dayCounts[days] > dayCount)
            {
                dayCount = dayCounts[days];
                busyDay = days;
            }
        }
        return busyDay;
    }
    
    /**
     * Returns the quietest day recorded in the log file
     * @return quietDay The quietest day of the year
     */
    public int quietestDay()
    {
        int[] dayCounts = new int[28];
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay() - 1;
            dayCounts[day]++;
        }
        
        int dayCount = dayCounts[0];
        int quietDay = 0;
        for(int days = 0; days < dayCounts.length; days++)
        {
            if(dayCounts[days] < dayCount)
            {
                dayCount = dayCounts[days];
                quietDay = days;
            }
        }
        return quietDay;
    }
    
    /**
     * Returns the busiest month recorded in the log file
     * @return busyMonth The busiest month of the year
     */
    public int busiestMonth()
    {
        int[] monthCounts = new int[12];
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth() - 1;
            monthCounts[month]++;
        }
        
        int monthCount = 0;
        int busyMonth = 0;
        for(int months = 0; months < monthCounts.length; months++)
        {
            if(monthCounts[months] > monthCount)
            {
                monthCount = monthCounts[months];
                busyMonth = months;
            }
        }
        return busyMonth;
    }
    
    /**
     * Returns the quietest month recorded in the log file
     * @return quietMonth The quietest month of the year
     */
    public int quietestMonth()
    {
        int[] monthCounts = new int[12];
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth() - 1;
            monthCounts[month]++;
        }
        
        int monthCount = monthCounts[0];
        int quietMonth = 0;
        for(int months = 0; months < monthCounts.length; months++)
        {
            if(monthCounts[months] < monthCount)
            {
                monthCount = monthCounts[months];
                quietMonth = months;
            }
        }
        return quietMonth;
    }
}
