
package bee.beeshroom.ComfyCozy.entity;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
//import bee.beeshroom.ComfyCozy.util.handlers.LootTableHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
//import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


	public class EntityOatmealSheep extends EntityAnimal implements net.minecraftforge.common.IShearable
	{                                             //changed DYE_COLOR to OAT_FLAVOR
	    private static final DataParameter<Byte> OAT_FLAVOR = EntityDataManager.<Byte>createKey(EntityOatmealSheep.class, DataSerializers.BYTE);
	    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT, ModItems.OATS, ModItems.STRAWBERRY, ModItems.CINNAMON);
	     //Internal crafting inventory used to check the result of mixing dyes corresponding to the fleece color when
	     //breeding sheep.
	     
	/*    private final InventoryCrafting inventoryCrafting = new InventoryCrafting(new Container()
	    {
	        
	         //Determines whether supplied player can use this container
	        
	        public boolean canInteractWith(EntityPlayer playerIn)
	        {
	            return false;
	        }
	    }, 2, 1);
	    // Map from EnumDyeColor to RGB values for passage to GlStateManager.color() */
	/*    private static final Map<EnumDyeColor, float[]> DYE_TO_RGB = Maps.newEnumMap(EnumDyeColor.class);   */
	    //
	     // Used to control movement as well as wool regrowth. Set to 40 on handleHealthUpdate and counts down with each
	     // tick.
	     //
	    private int sheepTimer;
	    private EntityAIEatGrass entityAIEatGrass;

/*	    private static float[] createOatFlavor(EnumDyeColor p_192020_0_)
	    {
	        float[] afloat = p_192020_0_.getColorComponentValues();
	        float f = 0.75F;
	        return new float[] {afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
	    }  */

	/*    @SideOnly(Side.CLIENT)
	    public static float[] getDyeRgb(EnumDyeColor dyeColor)
	    {
	        return DYE_TO_RGB.get(dyeColor);
	    }
	    */

	    public EntityOatmealSheep(World worldIn)
	    {
	        super(worldIn);
	        this.setSize(0.9F, 1.3F);
	  /*      this.inventoryCrafting.setInventorySlotContents(0, new ItemStack(Items.DYE));
	        this.inventoryCrafting.setInventorySlotContents(1, new ItemStack(Items.DYE));  */
	    }

	    protected void initEntityAI()
	    {
	        this.entityAIEatGrass = new EntityAIEatGrass(this);
	        this.tasks.addTask(0, new EntityAISwimming(this));
	        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
	        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
	       // this.tasks.addTask(3, new EntityAITempt(this, 1.1D, Items.WHEAT, false));
	        this.tasks.addTask(3, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
	      //  this.tasks.addTask(9, new EntityAIFollowParent(this, 0.6D));
	        // this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
	        this.tasks.addTask(5, this.entityAIEatGrass);
	        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
	        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
	        this.tasks.addTask(8, new EntityAILookIdle(this));
	        

	      //  this.tasks.addTask(7, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
	    }
	    
	    public boolean isBreedingItem(ItemStack stack)
	    {
	        return TEMPTATION_ITEMS.contains(stack.getItem());
	    }
	    
	    public boolean canTrample(World world, Block block, BlockPos pos, float fallDistance)
	    {
	        return false;
	    }

	    protected void updateAITasks()
	    {
	        this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
	        super.updateAITasks();
	    }

	    //
	     // Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	     // use this to react to sunlight and start to burn.
	     //
	    public void onLivingUpdate()
	    {
	        if (this.world.isRemote)
	        {
	            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
	        }
	        
	   /*     if (!this.onGround && this.motionY < 0.0D)
	        {
	            this.motionY *= 0.9D;
	        } */

	        super.onLivingUpdate();
	    }

	    protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.5D);
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
	    }

	    protected void entityInit()
	    {
	        super.entityInit();
	        this.dataManager.register(OAT_FLAVOR, Byte.valueOf((byte)0));
	    } 

	/*    @Nullable
	  protected ResourceLocation getLootTable()
	    {
	        if (this.getSheared())
	        {
	            //changed this from nothing to plain
	        	return LootTableHandler.ENTITIES_OATMEALSHEEP;
	        }
	     else
	       {
	    	 return LootTableHandler.ENTITIES_OATMEALSHEEP;
	    	// return LootTableHandler.ENTITIES_OATMEALSHEEP_PLAIN;
	       }
	      /*     switch (this.getOatFlavor())
	           {
	              case WHITE:
	                default:
	                    return LootTableHandler.ENTITIES_OATMEALSHEEP_PLAIN;
	          //      case PINK:
	           //         return LootTableHandler.ENTITIES_OATMEALSHEEP_PEACH;
	           } 
	        } */ 
	    //} 

	    //
	     // Handler for {@link World#setEntityState}
	    //
	    @SideOnly(Side.CLIENT)
	    public void handleStatusUpdate(byte id)
	    {
	        if (id == 10)
	        {
	            this.sheepTimer = 40;
	        }
	        else
	        {
	            super.handleStatusUpdate(id);
	        }
	    }

	/*    public boolean processInteract(EntityPlayer player, EnumHand hand)
	    {
	        ItemStack itemstack = player.getHeldItem(hand);

	        if (false && itemstack.getItem() == Items.SHEARS && !this.getSheared() && !this.isChild())   //Forge: Moved to onSheared
	        {
	            if (!this.world.isRemote)
	            {
	                this.setSheared(true);
	                int i = 1 + this.rand.nextInt(3);

	                for (int j = 0; j < i; ++j)
	                {
	                	// changed this v EntityItem entityitem = this.entityDropItem(new ItemStack(ModItems.OATS), 1, this.getOatFlavor().getMetadata()), 1.0F);
	                    EntityItem entityitem = this.entityDropItem(new ItemStack(ModItems.OATS), 1);
	                    entityitem.motionY += (double)(this.rand.nextFloat() * 0.05F);
	                    entityitem.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
	                    entityitem.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
	                }
	            }

	            itemstack.damageItem(1, player);
	            this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
	        }

	        return super.processInteract(player, hand);
	    }
	    */
	    
	    
	    
	    
	    public boolean processInteract(EntityPlayer player, EnumHand hand)
	    {
	        ItemStack itemstack = player.getHeldItem(hand);

	        if (itemstack.getItem() == Items.BOWL && this.getGrowingAge() >= 0 && !player.capabilities.isCreativeMode)
	        {
	            itemstack.shrink(1);

	            if (itemstack.isEmpty())
	            {
	                player.setHeldItem(hand, new ItemStack(ModItems.OATMEAL));
	            }
	            else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.OATMEAL)))
	            {
	                player.dropItem(new ItemStack(ModItems.OATMEAL), false);
	            }

	            return true;
	        }
	        else if (false && itemstack.getItem() == Items.SHEARS && this.getGrowingAge() >= 0) //Forge Disable, Moved to onSheared
	        {
	        	{
		            if (!this.world.isRemote)
		            {
		                this.setSheared(true);
		                int i = 1 + this.rand.nextInt(3);

		                for (int j = 0; j < i; ++j)
		                {
		                	// changed this v EntityItem entityitem = this.entityDropItem(new ItemStack(ModItems.OATS), 1, this.getOatFlavor().getMetadata()), 1.0F);
		                    EntityItem entityitem = this.entityDropItem(new ItemStack(ModItems.OATS), 1);
		                    entityitem.motionY += (double)(this.rand.nextFloat() * 0.05F);
		                    entityitem.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
		                    entityitem.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
		                }
		            }

		            itemstack.damageItem(1, player);
		            this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
		        }

	            return true;
	        }
	        else
	        {
	            return super.processInteract(player, hand);
	        }
	    }
	    
	    
	    
	    
	    

	    public static void registerFixesOatmealSheep(DataFixer fixer)
	    {
	        EntityLiving.registerFixesMob(fixer, EntityOatmealSheep.class);
	    }

	    @SideOnly(Side.CLIENT)
	    public float getHeadRotationPointY(float p_70894_1_)
	    {
	        if (this.sheepTimer <= 0)
	        {
	            return 0.0F;
	        }
	        else if (this.sheepTimer >= 4 && this.sheepTimer <= 36)
	        {
	            return 1.0F;
	        }
	        else
	        {
	            return this.sheepTimer < 4 ? ((float)this.sheepTimer - p_70894_1_) / 4.0F : -((float)(this.sheepTimer - 40) - p_70894_1_) / 4.0F;
	        }
	    }

	    @SideOnly(Side.CLIENT)
	    public float getHeadRotationAngleX(float p_70890_1_)
	    {
	        if (this.sheepTimer > 4 && this.sheepTimer <= 36)
	        {
	            float f = ((float)(this.sheepTimer - 4) - p_70890_1_) / 32.0F;
	            return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
	        }
	        else
	        {
	            return this.sheepTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F;
	        }
	    }

	    //
	     // (abstract) Protected helper method to write subclass entity data to NBT.
	     //
	    public void writeEntityToNBT(NBTTagCompound compound)
	    {
	        super.writeEntityToNBT(compound);
	        compound.setBoolean("Sheared", this.getSheared());
	  //      compound.setByte("Color", (byte)this.getOatFlavor().getMetadata());
	    }

	    //
	     // (abstract) Protected helper method to read subclass entity data from NBT.
	     //
	    public void readEntityFromNBT(NBTTagCompound compound)
	    {
	        super.readEntityFromNBT(compound);
	        this.setSheared(compound.getBoolean("Sheared"));
	      //  this.setOatFlavor(EnumDyeColor.byMetadata(compound.getByte("Color")));
	    }

	    protected SoundEvent getAmbientSound()
	    {
	        return SoundEvents.ENTITY_SHEEP_AMBIENT;
	    }

	    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	    {
	        return SoundEvents.ENTITY_SHEEP_HURT;
	    }

	    protected SoundEvent getDeathSound()
	    {
	        return SoundEvents.ENTITY_SHEEP_DEATH;
	    }

	    protected void playStepSound(BlockPos pos, Block blockIn)
	    {
	        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
	    }

	    //
	     // Gets the wool color of this sheep.
	    //
	    public EnumDyeColor getOatFlavor()
	    {
	        return EnumDyeColor.byMetadata(((Byte)this.dataManager.get(OAT_FLAVOR)).byteValue() & 15);
	    }
	    

	    //
	     // Sets the wool color of this sheep
	    //
	   public void setOatFlavor(EnumDyeColor color)
	    {
	        byte b0 = ((Byte)this.dataManager.get(OAT_FLAVOR)).byteValue();
	        this.dataManager.set(OAT_FLAVOR, Byte.valueOf((byte)(b0 & 240 | color.getMetadata() & 15)));
	    }

	    //
	    // returns true if a sheeps wool has been sheared
	     //
	    public boolean getSheared()
	    {
	        return (((Byte)this.dataManager.get(OAT_FLAVOR)).byteValue() & 16) != 0;
	    }

	   //
	     // make a sheep sheared if set to true
	     //
	    public void setSheared(boolean sheared)
	    {
	        byte b0 = ((Byte)this.dataManager.get(OAT_FLAVOR)).byteValue();

	        if (sheared)
	        {
	            this.dataManager.set(OAT_FLAVOR, Byte.valueOf((byte)(b0 | 16)));
	        }
	        else
	        {
	            this.dataManager.set(OAT_FLAVOR, Byte.valueOf((byte)(b0 & -17)));
	        }
	    } 

	  //
	    // Chooses a "vanilla" sheep color based on the provided random.
	     //
	        public static EnumDyeColor getRandomOatFlavor(Random random)
	    {
	        	return EnumDyeColor.WHITE;
	        	/*     int i = random.nextInt(100);

	     if (i < 5)
	        {
	            return EnumDyeColor.BLACK;
	        }
	        else if (i < 10)
	        {
	            return EnumDyeColor.GRAY;
	        }
	        else if (i < 15)
	        {
	            return EnumDyeColor.SILVER;
	        }
	        else if (i < 18)
	        {
	            return EnumDyeColor.BROWN;
	        }
	        else
	        {
	            return random.nextInt(500) == 0 ? EnumDyeColor.PINK : EnumDyeColor.WHITE;
	        }  */
	    }

	    public EntityOatmealSheep createChild(EntityAgeable ageable)
	    {
	        EntityOatmealSheep entityoatmealsheep = (EntityOatmealSheep)ageable;
	        EntityOatmealSheep entityoatmealsheep1 = new EntityOatmealSheep(this.world);
	        //entityoatmealsheep1.setOatFlavor(this.getDyeColorMixFromParents(this, entityoatmealsheep));
	        return entityoatmealsheep1;
	    }

	    //
	     // This function applies the benefits of growing back wool and faster growing up to the acting entity. (This
	     // function is used in the AIEatGrass)
	     //
	    public void eatGrassBonus()
	    {
	        this.setSheared(false);

	        if (this.isChild())
	        {
	            this.addGrowth(45);
	        }
	    }

	    //
	     // Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
	    // when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
	    //
	    @Nullable
	    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	    {
	        livingdata = super.onInitialSpawn(difficulty, livingdata);
	       this.setOatFlavor(getRandomOatFlavor(this.world.rand));
	        return livingdata;
	    }

	    @Override public boolean isShearable(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos){ return !this.getSheared() && !this.isChild(); }
	    @Override
	    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
	    {
	        this.setSheared(true);
	        int i = 1 + this.rand.nextInt(2);

	        java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
	        for (int j = 0; j < i; ++j)
	        	   //ret.add(new ItemStack(ModItems.OATS), 1, this.getOatFlavor().getMetadata());
	        	ret.add(new ItemStack(ModBlocks.OAT_BLOCK));

	        this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
	        return ret;
	    }

	   //
	     // Attempts to mix both parent sheep to come up with a mixed dye color.
	     //
	/*    private EnumDyeColor getDyeColorMixFromParents(EntityAnimal father, EntityAnimal mother)
	    {
	        int i = ((EntityOatmealSheep)father).getFleeceColor().getDyeDamage();
	        int j = ((EntityOatmealSheep)mother).getFleeceColor().getDyeDamage();
	        this.inventoryCrafting.getStackInSlot(0).setItemDamage(i);
	        this.inventoryCrafting.getStackInSlot(1).setItemDamage(j);
	        ItemStack itemstack = CraftingManager.findMatchingResult(this.inventoryCrafting, ((EntityOatmealSheep)father).world);
	        int k;

	        if (itemstack.getItem() == Items.DYE)
	        {
	            k = itemstack.getMetadata();
	        }
	        else
	        {
	            k = this.world.rand.nextBoolean() ? i : j;
	        }

	        return EnumDyeColor.byDyeDamage(k); 
	    } */

	    public float getEyeHeight()
	    {
	        return 0.95F * this.height;
	    }

	/*    static
	    {
	        for (EnumDyeColor enumdyecolor : EnumDyeColor.values())
	        {
	            DYE_TO_RGB.put(enumdyecolor, createOatFlavor(enumdyecolor));
	        }

	        DYE_TO_RGB.put(EnumDyeColor.WHITE, new float[] {0.9019608F, 0.9019608F, 0.9019608F});
	    } */
	

	    public void onStruckByLightning(EntityLightningBolt lightningBolt)
	    {
	    	 if (!this.world.isRemote && !this.isDead)
	        	 for (int l = 0; l < 4; ++l)
	        {
	    	int i = MathHelper.floor(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
            int j = MathHelper.floor(this.posY);
             int k = MathHelper.floor(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(i, j, k);
                
	                if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR)
	                {
	                	this.setDead(); 
	                	this.world.setBlockState(blockpos, ModBlocks.BOWL_OATMEAL.getDefaultState()); 
	                	 
	                }
	           }
	    }
	
	
	
	
	}
