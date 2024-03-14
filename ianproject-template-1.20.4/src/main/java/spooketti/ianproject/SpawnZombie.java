package spooketti.ianproject;

import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SpawnZombie extends Item
{
    public SpawnZombie(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        ZombieEntity zombie = new ZombieEntity(world);
        zombie.setPosition(5,5,5);
        world.spawnEntity(zombie);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
