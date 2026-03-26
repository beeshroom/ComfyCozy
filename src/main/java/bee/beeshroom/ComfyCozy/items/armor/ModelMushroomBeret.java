//Made with Blockbench
package bee.beeshroom.ComfyCozy.items.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMushroomBeret extends ModelBiped {
	private final ModelRenderer beret;

	public ModelMushroomBeret() {
		textureWidth = 128;
		textureHeight = 128;

		beret = new ModelRenderer(this);
		beret.setRotationPoint(0.0F, -8F, 0.0F);
		
		// red mushroom
		beret.cubeList.add(new ModelBox(beret, 0, 80, -6.0F, -24.0F, 2.0F, 16, 16, 0, 0.0F, false));
		beret.cubeList.add(new ModelBox(beret, 32, 48, 2.0F, -24.0F, -6.0F, 0, 16, 16, 0.0F, false));

		// brown mushroom
		beret.cubeList.add(new ModelBox(beret, 64, 64, -10.0F, -24.0F, -2.0F, 16, 16, 0, 0.0F, false));
		beret.cubeList.add(new ModelBox(beret, 0, 48, -2.0F, -24.0F, -10.0F, 0, 16, 16, 0.0F, false));
	}

	// render code adapted from Quark's Witch Hat
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bipedHead.showModel = false;
		bipedHeadwear = beret;
		
		super.render(entity, f, f1, f2, f3, f4, f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}