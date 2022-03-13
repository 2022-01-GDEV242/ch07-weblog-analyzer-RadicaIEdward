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
    public LogAnalyzer(String logFile, int logSize)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[logSize];
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
     */
    public int busiestHour()
    {
        int busyHour = 0;
        for(int hours = 0; hours < hourCounts.length ;hours++)
        {
            if(hourCounts[hours] > busyHour)
            {
                busyHour = hourCounts[hours];
            }
        }
        return busyHour;
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
}
