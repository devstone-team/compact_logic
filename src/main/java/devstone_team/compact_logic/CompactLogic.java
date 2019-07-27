package devstone_team.compact_logic;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.ObjectHolder;

// so not allowed to have non-registrable members in an object holder annotated class.
// ie static final String a = "foo" crashes. lol.

@ObjectHolder(CompactLogicMod.MODID)
public class CompactLogic {
	
	public static final ScriptedLogicBlock scripted_logic_block = null;
	public static final ItemBlock scripted_logic_itemblock = null;

}
