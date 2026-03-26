package bee.beeshroom.ComfyCozy.entity;

import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.WorldServer;

public class EntityDirtyPig extends EntityPig
{
	public int timeUntilNextEgg;
	
    public EntityDirtyPig(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 0.9F);
        timeUntilNextEgg = this.rand.nextInt(2000) + 4000;
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("EggLayTime", this.timeUntilNextEgg);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("EggLayTime"))
        {
            this.timeUntilNextEgg = compound.getInteger("EggLayTime");
        }
    }

    public EntityDirtyPig createChild(EntityAgeable ageable)
    {
        return new EntityDirtyPig(this.world);
    }
    
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if (!world.isRemote)
        {
	        if (!world.isRemote && !isChild() && --timeUntilNextEgg <= 0)
	    	{
	    		playSound(SoundsHandler.DIRTY, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
	            dropItem(Item.getItemFromBlock(Blocks.DIRT), 1);
	            timeUntilNextEgg = this.rand.nextInt(2000) + 4000;
	        	
	        	IBlockState iblockstate = Blocks.DIRT.getBlockState().getBaseState();
	        	for (int i = 0; i < 15; ++i) {
	        		((WorldServer)world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width * 1.25D, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width * 1.25D, 1, 0.0D, 0.0D, 0.0D, 0.0D, Block.getStateId(iblockstate));
	        	}
	        }
	        
	        if (isInWater() && !isDead) // water washes the pig
	        {
	        	EntityPig entitypig = new EntityPig(world);
	        	
	        	entitypig.setSaddled(this.getSaddled());
	        	entitypig.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
	        	entitypig.setNoAI(this.isAIDisabled());
	        	entitypig.updatePassenger(this.getControllingPassenger());
	
	            if (this.hasCustomName())
	            {
	            	entitypig.setCustomNameTag(this.getCustomNameTag());
	            	entitypig.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
	            }
	            
	            for (int i = 0; i < 15; ++i)
	            {
	                double d0 = rand.nextGaussian() * 0.02D;
	                double d1 = rand.nextGaussian() * 0.02D;
	                double d2 = rand.nextGaussian() * 0.02D;
	                ((WorldServer)world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, (posX - this.width / 2) + rand.nextFloat(), (posY + this.height / 2) + rand.nextFloat(), (posZ - this.width / 2) + rand.nextFloat(), 1, d0, d1, d2, 0.0D);
	            }
	            
	            this.dropItem(Item.getItemFromBlock(Blocks.DIRT), 1);
	        	this.world.spawnEntity(entitypig);
	        	
	        	this.setDead();
	   		}
        }
    }
}
