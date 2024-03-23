package spooketti.ianproject.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import spooketti.ianproject.SerialStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class ZombieDie {
	@Inject(at = @At("HEAD"), method = "onDeath")
	private void onDeath(DamageSource source, CallbackInfo info) {
		LivingEntity entity = (LivingEntity)(Object) this;
		try {
			System.out.println(entity.getCustomName().getLiteralString());
			SerialStatus.greenComplete = !SerialStatus.greenComplete;
		} catch (Exception e) 
		{

		}
	}
}