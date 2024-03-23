package spooketti.ianproject;
import jssc.*;
public class SerialStatus 
{
    public static boolean redComplete = false;
    public static boolean blueComplete = false;
    public static boolean greenComplete = false;
    public static boolean yellowComplete = false;
    public static SerialPort arduino = new SerialPort("COM3");

    public static void pushToArduino()
    {
        //arduino.writeString(null);
    }
}
