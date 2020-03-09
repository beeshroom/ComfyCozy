/* package bee.beeshroom.ComfyCozy.entity.models;
//Made with Blockbench

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMushroomBeret extends ModelBiped {
	private final ModelRenderer redmushroom;
	private final ModelRenderer brownmushroom;

	public ModelMushroomBeret() {
		textureWidth = 64;
		textureHeight = 64;

		redmushroom = new ModelRenderer(this);
		redmushroom.setRotationPoint(0F, -8F, 0F);
		redmushroom.cubeList.add(new ModelBox(redmushroom, 0, 16, 2.0F, -16.0F, -6.0F, 0, 16, 16, 0.0F, false));
		redmushroom.cubeList.add(new ModelBox(redmushroom, 32, 32, -6.0F, -16.0F, 2.0F, 16, 16, 0, 0.0F, false));

		brownmushroom = new ModelRenderer(this);
		brownmushroom.setRotationPoint(0F, -8F, 0F);
		brownmushroom.cubeList.add(new ModelBox(brownmushroom, 0, 0, -2.0F, -16.0F, -10.0F, 0, 16, 16, 0.0F, false));
		brownmushroom.cubeList.add(new ModelBox(brownmushroom, 0, 0, -10.0F, -16.0F, -2.0F, 16, 16, 0, 0.0F, false));
		
		this.bipedHead.addChild(redmushroom);
		this.bipedHead.addChild(brownmushroom);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		//redmushroom.render(f5);
		//brownmushroom.render(f5);
		super.render(entity, f, f1, f2, f3, f4, f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
} */