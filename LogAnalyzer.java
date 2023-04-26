/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts; //this allows us to parse days
    private int[] monthCounts; //this allows us to parse months
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
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
     * this segment should read the name of the file
     */
    public LogAnalyzer(String filename)
    {
        hourCounts=new int[24]; //this sets the number of countable hours from 0 to 24
        reader=new LogfileReader(filename);
    }
    
    /**
     * creates a counter that tracks the number of times the log file is accessed
     */
    public int numberOfAccess()
    {
        int total=0; //sets the default number of access at zero
            for(int numAccesses:hourCounts)
            {
                    total += numAccesses; //each time that access occurs add a value to counter and save that as total
            }
            return total; //pass the total number of accessess back
    }
    
    /**
     * log the busiest month as per the log file
     */
    public int busiestMonth()
    {
        int busiestMonth=0; //set the counter at zero this signifies January 
        int maxCount=0; //set the value of the maximum count at zero to be used by counter
        
        for(int i=0; i<monthCounts.length;i++) //set up the counter for each time a month occurs when reading log
        {
            if(monthCounts[i]<maxCount)
            {
                busiestMonth=i;
                maxCount=monthCounts[i];
            }
        }
        return busiestMonth;
    }
    
    /**
     * log the slowest month as per the log file
     */
    public int slowestMonth()
    {
        int slowestMonth=0; //set the counter at zero this signifies January 
        int maxCount=0; //set the minimum count at zero to be used by the counter
        
        for(int i=0; i<monthCounts.length;i++) //set up the counter for each time a month occurs when reading log
        {
            if(monthCounts[i]<maxCount)
            {
                slowestMonth=i;
                maxCount=monthCounts[i];
            }
        }
        return slowestMonth;
    }
    
    /**
     * log the busiest day as per the log file
     */
    public int busiestDay()
    {
        int busiestDay=0; //set this counter at the first day of a month
        int maxCount=0; //sets the counter for each individual day at zero
        
        for(int i=0; i<dayCounts.length;i++) //start the numeric counter for each time a day is referenced from log file to track
        {
            if(dayCounts[i]<maxCount)
            {
                busiestDay=i;
                maxCount=dayCounts[i];
            }
        }
        return busiestDay;
    }
    
    /**
     * log the slowest day as per the log file
     */
     public int slowestDay()
    {
        int slowestDay=0; //set this counter at the first day of a month
        int maxCount=0; //sets the counter for each individual day at zero
        
        for(int i=0; i<dayCounts.length;i++) //start the numeric counter for each time a day is referenced from log file to track
        {
            if(dayCounts[i]<maxCount)
            {
                slowestDay=i;
                maxCount=dayCounts[i];
            }
        }
        return slowestDay;
    }
    
    /**
     * log the busiest hour as per the log file
     */
    
    /**
     * log the slowest hour as per the log file
     */
    
    /** 
     * log the busiest two hour block as per the log file
     */
    
    public int busiestBlock()
    {
        int busiestBlock=0;
        int maxCount=0;
        
        for(int i=0; i<hourCounts.length/2;i++)
        {
            int busiestCount=hourCounts[2*i]+hourCounts[1+2*i];
            if(busiestCount>maxCount)
            {
                busiestBlock=i;
            }
        }
        return busiestBlock;
    } 
}
