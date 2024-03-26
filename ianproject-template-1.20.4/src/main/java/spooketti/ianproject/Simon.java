package spooketti.ianproject;

import java.util.HashMap;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spooketti.ianproject.block.JCho;

public class Simon
{
 public static String systemPattern = "";
 private static String currentPattern = "";
 private static World world;

 public static char[] SimonChoice = {'R','Y','G','B'};
 public static HashMap<Character, Integer> choiceMap = new HashMap<>();
 private static final BlockPos RedPos = new BlockPos(-10,-57,7);
    private static final BlockPos YellowPos = new BlockPos(-10,-57,5);
    private static final BlockPos GreenPos = new BlockPos(-10,-57,3);
    private static final BlockPos BluePos = new BlockPos(-10,-57,1);
   private static final BlockPos[] posArr = {RedPos,YellowPos,GreenPos,BluePos};
public static int sysPos = 0;
public static boolean reading = false;

 public static void resetSimon()
 {
    systemPattern = "";
    currentPattern = "";
    SerialStatus.redComplete = false;
 }
 
 public static void initWorld(World wrld)
 {
  systemPattern = "";
  choiceMap.clear();
   choiceMap.put('R',7);
   choiceMap.put('Y',5);
   choiceMap.put('G',3);
   choiceMap.put('B',1);
   world = wrld;
   for(int i=0;i<4;i++)
   {
      world.setBlockState(posArr[i], world.getBlockState(posArr[i]).with(JCho.colorType,i).with(JCho.charged,false));
   }
   beginSimon();
 }

 public static void beginSimon()
 {
    resetSimon();
    readSimon();
 }


 private static void readSimon()
 {
   reading = true;
   sysPos = 0;
   currentPattern = "";
   systemPattern += SimonChoice[(int)Math.floor(Math.random()*SimonChoice.length)];
   BlockPos pos = new BlockPos(-10,-57,choiceMap.get(systemPattern.charAt(0)));
   world.setBlockState(pos, world.getBlockState(pos).with(JCho.charged, true));
   world.scheduleBlockTick(pos,world.getBlockState(pos).getBlock(),30);
 }

 public static void writeSimon(char choice,BlockPos pos)
 {
    currentPattern += choice;
    sysPos++;
    if(currentPattern.equals(systemPattern))
    {
      world.setBlockState(pos, world.getBlockState(pos).with(JCho.charged,false));
      readSimon();
      return;
    }
    if(choice != systemPattern.charAt(sysPos))
    {
      initWorld(world);
    }
 }

}
