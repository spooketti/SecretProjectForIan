package spooketti.ianproject;

public class Simon 
{
 private static String systemPattern = "";
 private static String currentPattern = "";

 private static char[] SimonChoice = {'R','G','B','Y'};
 //private static int simonPos = 0;

 public static void resetSimon()
 {
    systemPattern = "";
    currentPattern = "";
 }

 public static void failSimon()
 {
    SerialStatus.redComplete = false;
 }

 public static void beginSimon()
 {
    resetSimon();
    readSimon();
 }


 private static void readSimon()
 {
    systemPattern += Math.floor(Math.random()*SimonChoice.length);
    for(int i=0;i<systemPattern.length();i++)
    {
        switch(systemPattern.charAt(i))
        {
            case 'R':
            
            break;

            case 'B':
            
            break;

            case 'G':
            
            break;

            case 'Y':
            
            break;
        }
    }
    
 }

 public static void writeSimon(char choice)
 {
    currentPattern += choice;
    if(systemPattern.charAt(currentPattern.length()-1)!=choice)
    {
        failSimon();
        return;
    }
    if(currentPattern.length() >= 5)
    {
        SerialStatus.redComplete = true;
    }
 }

}
