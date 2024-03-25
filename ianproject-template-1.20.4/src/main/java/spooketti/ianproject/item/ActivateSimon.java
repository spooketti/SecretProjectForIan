package spooketti.ianproject.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import spooketti.ianproject.Simon;
public class ActivateSimon extends Item
{
    public ActivateSimon(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        //Simon.initWorld(world);
        playerEntity.getItemCooldownManager().set(this, 20);
        Simon.initWorld(world);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
