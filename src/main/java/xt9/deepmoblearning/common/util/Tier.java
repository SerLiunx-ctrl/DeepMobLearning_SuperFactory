package xt9.deepmoblearning.common.util;

import net.minecraft.client.resources.I18n;
import xt9.deepmoblearning.common.config.Config;

/**
 * Created by xt9 on 2018-03-25.
 * DataModel tiers, used for datamodels & TileEntityRelation keys
 */
public class Tier {
    public static int getPristineChance(int tier) {
        switch(tier) {
            case 0: return 0;
            case 1: return Config.pristineChance.get("tier1").getInt();
            case 2: return Config.pristineChance.get("tier2").getInt();
            case 3: return Config.pristineChance.get("tier3").getInt();
            case 4: return Config.pristineChance.get("tier4").getInt();
            default: return 0;
        }
    }

    public static String getTierName(int tier, boolean getNextTierName) {
        int addTiers = getNextTierName ? 1 : 0;
        switch(tier + addTiers) {
            case 1: return I18n.format("deepmoblearning.data_model.tiername.1");
            case 2: return I18n.format("deepmoblearning.data_model.tiername.2");
            case 3: return I18n.format("deepmoblearning.data_model.tiername.3");
            case 4: return I18n.format("deepmoblearning.data_model.tiername.4");
            default: return I18n.format("deepmoblearning.data_model.tiername.0");
        }
    }

    public static boolean isMaxTier(int tier) {
        return tier == 4;
    }
}
