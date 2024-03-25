package spooketti.ianproject.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import spooketti.ianproject.Simon;

public class JCho extends Block {

    public JCho(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(charged, false).with(colorType,0));
    }

    public static final IntProperty colorType = IntProperty.of("color", 0, 3);
    public static final BooleanProperty charged = BooleanProperty.of("charged");
    

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (!world.isClient) {
            Simon.writeSimon(Simon.SimonChoice[(int)state.get(colorType)]);
            world.scheduleBlockTick(pos,this,10);
            world.setBlockState(pos, state.with(charged,true));

        return ActionResult.SUCCESS;
    }
    return ActionResult.FAIL;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        world.setBlockState(pos, state.with(charged,false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(charged);
        builder.add(colorType);
    }

}
