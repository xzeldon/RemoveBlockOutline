package ru.xzeldon.removeblockoutline.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.xzeldon.removeblockoutline.RemoveBlockOutlineClient;
import ru.xzeldon.removeblockoutline.config.RemoveBlockOutlineConfig;

@Mixin(WorldRenderer.class)
public class RemoveBlockOutlineMixin {
	private static RemoveBlockOutlineConfig config;

	@Inject(method = "drawBlockOutline", at = @At("HEAD"), cancellable = true)
	private void onDrawBlockOutline(MatrixStack matrixStack, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos blockPos, BlockState blockState, CallbackInfo ci) {
		config = RemoveBlockOutlineClient.CONFIG;

		if (!config.isEnableBlockOutline()) {
			ci.cancel();
		}
	}
}
