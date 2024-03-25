package spooketti.ianproject.item;

import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import spooketti.ianproject.LampBinary;
import spooketti.ianproject.SerialStatus;
public class SpawnZombie extends Item
{
    public SpawnZombie(Settings settings)
    {
        super(settings);
    }

    public static ZombieEntity zombie;

    public static void zombieInBox(World world, Box greenRoom)
    {
        for(ZombieEntity zom : world.getEntitiesByClass(ZombieEntity.class, greenRoom, entity -> true))
        {
            zom.kill();
        }
        zombie = new ZombieEntity(world);
        zombie.setPosition(11.5, -58, -16);
        zombie.setCustomName(Text.of("死"));
        world.spawnEntity(zombie);
    }

    public static void deathReset()
    {

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        Box greenRoom = new Box(8,-60,-19,14,-52,-5);
        if(!greenRoom.contains(playerEntity.getPos().x,playerEntity.getPos().y,playerEntity.getPos().z))
        {
            return TypedActionResult.fail(playerEntity.getStackInHand(hand));
        }
        SerialStatus.greenComplete = true;
        zombieInBox(world, greenRoom);
        LampBinary.initBinary(world);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
