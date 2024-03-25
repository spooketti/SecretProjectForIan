package spooketti.ianproject;

import java.util.HashMap;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spooketti.ianproject.block.JCho;

public class Simon 
{
 private static String systemPattern = "";
 private static String currentPattern = "";
 private static World world;

 public static char[] SimonChoice = {'R','Y','G','B'};
 private static HashMap<Character, Integer> choiceMap = new HashMap<>();
 private static final BlockPos RedPos = new BlockPos(-10,-57,7);
    private static final BlockPos YellowPos = new BlockPos(-10,-57,5);
    private static final BlockPos GreenPos = new BlockPos(-10,-57,3);
    private static final BlockPos BluePos = new BlockPos(-10,-57,1);

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
   world.setBlockState(new BlockPos(-10,-57,7), world.getBlockState(new BlockPos(-10,-57,7)).with(JCho.colorType,0));
   world.setBlockState(new BlockPos(-10,-57,5), world.getBlockState(new BlockPos(-10,-57,5)).with(JCho.colorType,1));
   world.setBlockState(new BlockPos(-10,-57,3), world.getBlockState(new BlockPos(-10,-57,3)).with(JCho.colorType,2));
   world.setBlockState(new BlockPos(-10,-57,1), world.getBlockState(new BlockPos(-10,-57,1)).with(JCho.colorType,3));
   beginSimon();
 }

 public static void beginSimon()
 {
    resetSimon();
    readSimon();
 }


 private static void readSimon()
 {
   currentPattern = "";
    systemPattern += SimonChoice[(int)Math.floor(Math.random()*SimonChoice.length)];
    for(int i=0;i<systemPattern.length();i++)
    {
      char choice = systemPattern.charAt(i);
      BlockPos pos = new BlockPos(-10,-57,choiceMap.get(choice));
      world.setBlockState(pos, world.getBlockState(pos).with(JCho.charged, true));
      world.scheduleBlockTick(pos, world.getBlockState(pos).with(JCho.charged,true).getBlock(), 30);
    }
 }

 public static void writeSimon(char choice)
 {
    currentPattern += choice;
    System.out.println(systemPattern);
    if(systemPattern.charAt(currentPattern.length()-1)!=choice)
    {
        initWorld(world);
        return;
    }
    if(systemPattern.equals(currentPattern))
    {
      if(currentPattern.length() >= 5)
      {
          SerialStatus.redComplete = true;
          return;
      }
      readSimon();
      return;
    }
 }

}
