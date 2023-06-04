package com.rcx.psionicolor.block;

/*import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.api.internal.PsiRenderHelper;
import vazkii.psi.common.Psi;
import vazkii.psi.common.block.tile.TileConjured;

public class BlockConjuredSolid extends Block {

	public static final BooleanProperty BLOCK_UP = BooleanProperty.create("block_up");
	public static final BooleanProperty BLOCK_DOWN = BooleanProperty.create("block_down");
	public static final BooleanProperty BLOCK_NORTH = BooleanProperty.create("block_north");
	public static final BooleanProperty BLOCK_SOUTH = BooleanProperty.create("block_south");
	public static final BooleanProperty BLOCK_WEST = BooleanProperty.create("block_west");
	public static final BooleanProperty BLOCK_EAST = BooleanProperty.create("block_east");

	public BlockConjuredSolid(Properties properties) {
		super(properties);
		setDefaultState(getStateContainer().getBaseState().with(BLOCK_DOWN, false).with(BLOCK_UP, false).with(BLOCK_EAST, false).with(BLOCK_WEST, false).with(BLOCK_NORTH, false).with(BLOCK_SOUTH, false));
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.isIn(this) ? true : super.isSideInvisible(state, adjacentBlockState, side);
	}

	@Nullable
	@Override
	public float[] getBeaconColorMultiplier(BlockState state, IWorldReader world, BlockPos pos, BlockPos beaconPos) {
		TileEntity inWorld = world.getTileEntity(pos);
		if (inWorld instanceof TileConjured) {
			int color = Psi.proxy.getColorForColorizer(((TileConjured) inWorld).colorizer);
			return new float[] { PsiRenderHelper.r(color) / 255F, PsiRenderHelper.g(color) / 255F, PsiRenderHelper.b(color) / 255F };
		}
		return null;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		worldIn.getChunkProvider().markBlockChanged(pos);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		world.removeBlock(pos, false);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BLOCK_UP, BLOCK_DOWN, BLOCK_NORTH, BLOCK_SOUTH, BLOCK_WEST, BLOCK_EAST);
	}

	@Override
	public boolean isTransparent(BlockState state) {
		return true;
	}

	@Nonnull
	@Override
	public BlockState updatePostPlacement(@Nonnull BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		BooleanProperty prop;
		switch (facing) {
		default:
		case DOWN:
			prop = BLOCK_DOWN;
			break;
		case UP:
			prop = BLOCK_UP;
			break;
		case NORTH:
			prop = BLOCK_NORTH;
			break;
		case SOUTH:
			prop = BLOCK_SOUTH;
			break;
		case WEST:
			prop = BLOCK_WEST;
			break;
		case EAST:
			prop = BLOCK_EAST;
			break;
		}
		if (state.getBlock() == facingState.getBlock()) {
			return state.with(prop, true);
		} else {
			return state.with(prop, false);
		}
	}

	@Nonnull
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.fullCube();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.fullCube();
	}

	@Override
	public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.empty();
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1.0F;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(@Nonnull BlockState state, IBlockReader world) {
		return new TileConjured();
	}
}*/
