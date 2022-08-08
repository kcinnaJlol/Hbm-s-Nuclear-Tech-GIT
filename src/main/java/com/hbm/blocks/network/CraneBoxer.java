package com.hbm.blocks.network;

import com.hbm.lib.RefStrings;
import com.hbm.tileentity.network.TileEntityCraneBoxer;

import api.hbm.conveyor.IConveyorItem;
import api.hbm.conveyor.IConveyorPackage;
import api.hbm.conveyor.IEnterableBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class CraneBoxer extends BlockCraneBase implements IEnterableBlock {

	public CraneBoxer() {
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCraneBoxer();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		super.registerBlockIcons(iconRegister);
		this.iconIn = iconRegister.registerIcon(RefStrings.MODID + ":crane_box");
		this.iconSideIn = iconRegister.registerIcon(RefStrings.MODID + ":crane_side_box");
		this.iconDirectional = iconRegister.registerIcon(RefStrings.MODID + ":crane_boxer_top");
		this.iconDirectionalUp = iconRegister.registerIcon(RefStrings.MODID + ":crane_boxer_side_up");
		this.iconDirectionalDown = iconRegister.registerIcon(RefStrings.MODID + ":crane_boxer_side_down");
	}

	@Override
	public int getRotationFromSide(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		
		if(meta > 1 && side == 1) {
			if(meta == 2) return 3;
			if(meta == 3) return 0;
			if(meta == 4) return 1;
			if(meta == 5) return 2;
		}
		
		return 0;
	}

	@Override
	public boolean canItemEnter(World world, int x, int y, int z, ForgeDirection dir, IConveyorItem entity) {
		ForgeDirection orientation = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
		return orientation == dir;
	}

	@Override
	public void onItemEnter(World world, int x, int y, int z, ForgeDirection dir, IConveyorItem entity) {
		
	}

	@Override
	public boolean canPackageEnter(World world, int x, int y, int z, ForgeDirection dir, IConveyorPackage entity) {
		return false;
	}

	@Override
	public void onPackageEnter(World world, int x, int y, int z, ForgeDirection dir, IConveyorPackage entity) { }
}
