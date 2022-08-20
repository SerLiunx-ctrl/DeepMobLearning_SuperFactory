package xt9.deepmoblearning.common.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import xt9.deepmoblearning.DeepConstants;
import xt9.deepmoblearning.DeepMobLearning;
import xt9.deepmoblearning.common.Registry;
import xt9.deepmoblearning.common.capabilities.IPlayerTrial;
import xt9.deepmoblearning.common.capabilities.PlayerTrialProvider;
import xt9.deepmoblearning.common.config.Config;
import xt9.deepmoblearning.common.mobmetas.MobMetaData;
import xt9.deepmoblearning.common.util.DataModel;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by xt9 on 2018-05-16.
 */
public class ItemGlitchArmor extends ItemArmor {
    private static final int PRISTINE_SET_CHANCE = 18;
    private static final int PRISTINE_SET_NUMBER_OF_DROPS = 2;

    private static ArmorMaterial material = EnumHelper.addArmorMaterial(
        "GLITCH_INFUSED_MATERIAL",
        DeepConstants.MODID + ":" + "glitch_infused",
        120,
        new int[]{3, 8, 6, 3},
        15,
        null,
        3.0F
    );

    public ItemGlitchArmor(String itemName, EntityEquipmentSlot slot) {
        super(material, 0, slot);
        setUnlocalizedName(DeepConstants.MODID + "." + itemName);
        setCreativeTab(DeepMobLearning.creativeTab);
        setRegistryName(itemName);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.add(I18n.format("deepmoblearning.tooltips.glitch_armor.line1"));
        list.add(I18n.format("deepmoblearning.tooltips.glitch_armor.line2"));
        list.add(I18n.format("deepmoblearning.tooltips.glitch_armor.line3")
                .replace("{chance}", PRISTINE_SET_CHANCE + "%"));
        list.add(I18n.format("deepmoblearning.tooltips.glitch_armor.line4") + PRISTINE_SET_NUMBER_OF_DROPS);
        list.add(I18n.format("deepmoblearning.tooltips.glitch_armor.line5"));
        list.add(I18n.format("deepmoblearning.tooltips.glitch_armor.line6"));
    }

    public static boolean isSetEquippedByPlayer(EntityPlayer player) {
        return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemGlitchArmor &&
            player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemGlitchArmor &&
            player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemGlitchArmor &&
            player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemGlitchArmor;
    }

    public static void dropPristineMatter(World world, BlockPos position, ItemStack stack, EntityPlayer player) {
        if(ThreadLocalRandom.current().nextInt(1, 100) <= PRISTINE_SET_CHANCE) {
            MobMetaData meta = DataModel.getMobMetaData(stack);
            EntityItem drop = new EntityItem(world, position.getX(), position.getY(), position.getZ(), meta.getPristineMatterStack(PRISTINE_SET_NUMBER_OF_DROPS));
            drop.setDefaultPickupDelay();
            world.spawnEntity(drop);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() instanceof ItemGlitchIngot;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if(isInCreativeTab(tab)) {
            list.add(new ItemStack(this));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName();
    }

    public static class ItemGlitchHelmet extends ItemGlitchArmor {
        public ItemGlitchHelmet() {
            super("glitch_infused_helmet", EntityEquipmentSlot.HEAD);
        }

        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
            super.addInformation(stack, worldIn, list, flagIn);
        }
    }

    public static class ItemGlitchChestplate extends ItemGlitchArmor {
        public ItemGlitchChestplate() {
            super("glitch_infused_chestplate", EntityEquipmentSlot.CHEST);
        }

        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
            super.addInformation(stack, worldIn, list, flagIn);
        }
    }

    public static class ItemGlitchLeggings extends ItemGlitchArmor {
        public ItemGlitchLeggings() {
            super("glitch_infused_leggings", EntityEquipmentSlot.LEGS);
        }

        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
            super.addInformation(stack, worldIn, list, flagIn);
        }
    }

    public static class ItemGlitchBoots extends ItemGlitchArmor {
        public ItemGlitchBoots() {
            super("glitch_infused_boots", EntityEquipmentSlot.FEET);
        }

        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
            super.addInformation(stack, worldIn, list, flagIn);
        }
    }

}
