package devstone_team.compact_logic;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CompactLogicMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ScriptedLogicBlock extends Block {

	public ScriptedLogicBlock() {
		super(Block.Properties.create(Material.CIRCUITS, MaterialColor.RED).hardnessAndResistance(2).sound(SoundType.METAL));
		this.setRegistryName(CompactLogicMod.MODID,  "scripted_logic_block");
	}
	
}
