//Made with Blockbench
package bee.beeshroom.ComfyCozy.items.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMushroomBeret extends ModelBiped {
//	private final ModelRenderer bb_main;
	private final ModelRenderer redmushroom;
	private final ModelRenderer brownmushroom;

	public ModelMushroomBeret() {
		textureWidth = 128;
		textureHeight = 128;

	/*	bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -74.0F, -64.0F, 0.0F, 64, 64, 0, 0.0F, false));
*/
		redmushroom = new ModelRenderer(this);
		redmushroom.setRotationPoint(0.0F, -8F, 0.0F);
		redmushroom.cubeList.add(new ModelBox(redmushroom, 0, 80, -6.0F, -16.0F, 2.0F, 16, 16, 0, 0.0F, false));
		redmushroom.cubeList.add(new ModelBox(redmushroom, 32, 48, 2.0F, -16.0F, -6.0F, 0, 16, 16, 0.0F, false));

		brownmushroom = new ModelRenderer(this);
		brownmushroom.setRotationPoint(0.0F, -8F, 0.0F);
		brownmushroom.cubeList.add(new ModelBox(brownmushroom, 64, 64, -10.0F, -16.0F, -2.0F, 16, 16, 0, 0.0F, false));
		brownmushroom.cubeList.add(new ModelBox(brownmushroom, 0, 48, -2.0F, -16.0F, -10.0F, 0, 16, 16, 0.0F, false));
		
		this.bipedHead.addChild(redmushroom);
		this.bipedHead.addChild(brownmushroom);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}