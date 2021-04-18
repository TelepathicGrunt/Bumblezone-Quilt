package net.telepathicgrunt.bumblezone.entities;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Items;
import net.telepathicgrunt.bumblezone.modinit.BzItems;
import net.telepathicgrunt.bumblezone.utils.GeneralUtils;

public class WanderingTrades {

    public static void addWanderingTrades(){

        // Villagers
        // Level 3 trades
        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            factories.add(new GeneralUtils.BasicItemTrade(
                    Items.EMERALD,
                    20,
                    BzItems.MUSIC_DISC_FLIGHT_OF_THE_BUMBLEBEE_RIMSKY_KORSAKOV,
                    1,
                    1,
                    10,
                    0.5F
            ));

            factories.add(new GeneralUtils.BasicItemTrade(
                    Items.EMERALD,
                    20,
                    BzItems.MUSIC_DISC_HONEY_BEE_RAT_FACED_BOY,
                    1,
                    1,
                    10,
                    0.5F
            ));
        });
    }
}
