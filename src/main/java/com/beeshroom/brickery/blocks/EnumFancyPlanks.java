package com.beeshroom.brickery.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum EnumFancyPlanks implements IStringSerializable {

    OAK(0, "oak", "Parquet"),
    SPRUCE(1, "spruce", "Basket-weave"),
    BIRCH(2, "birch", "Checkered"),
    JUNGLE(3, "jungle", "Diagonal"),
    ACACIA(4, "acacia", "Herringbone"),
    DARK_OAK(5, "dark_oak", "Laminate");

    public String name, type;
    public int ID;

    EnumFancyPlanks(int ID, String name, String type) {
        this.name = name;
        this.ID = ID;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getID() {
        return ID;
    }

}