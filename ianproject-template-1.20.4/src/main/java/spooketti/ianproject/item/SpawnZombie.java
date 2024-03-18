package spooketti.ianproject.item;

import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import spooketti.ianproject.LampBinary;
public class SpawnZombie extends Item
{
    public SpawnZombie(Settings settings)
    {
        super(settings);
    }

    public static void zombieInBox(World world, Box greenRoom)
    {
        for(ZombieEntity zombie : world.getEntitiesByClass(ZombieEntity.class, greenRoom, entity -> true))
        {
            zombie.kill();
        }
        ZombieEntity zombie = new ZombieEntity(world);
        zombie.setPosition(0,0,0);
        world.spawnEntity(zombie);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        Box greenRoom = new Box(0,0,0,5,5,5);
        if(!greenRoom.contains(playerEntity.getPos().x,playerEntity.getPos().y,playerEntity.getPos().z))
        {
            return TypedActionResult.fail(playerEntity.getStackInHand(hand));
        }
        zombieInBox(world, greenRoom);
        LampBinary.initBinary(world);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
