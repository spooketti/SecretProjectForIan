package spooketti.ianproject;

import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.state.property.Properties;

public class LampBinary
{

   private static int binaryCode = (int)Math.floor(Math.random() * 129);
   private static String binaryString;
   private static final boolean[] decimalBool = {false,true};

   public static void initBinary(World world)
   {
      binaryCode = (int)Math.floor(Math.random()*129);
      binaryString = Integer.toBinaryString(binaryCode);
      while(binaryString.length() < 8)
      {
         binaryString = "0" + binaryString;
      }
      for(int i=0;i<8;i++)
      {
         world.setBlockState(new BlockPos(i,0,0), Blocks.COPPER_BULB.getDefaultState().with(Properties.LIT,decimalBool[binaryString.charAt(i) - '0']));
      }
   }

   public static void handleChat(SignedMessage message, ServerPlayerEntity playerEntity, MessageType.Parameters messagetype)
   {
      Text signedMessage = message.unsignedContent();
      //System.out.println(binaryCode);
      if(Integer.parseInt(signedMessage.toString()) == binaryCode)
      {
         playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,50,1));
      }
      else
      {
         System.out.println(signedMessage.toString());
      }
   }
}
