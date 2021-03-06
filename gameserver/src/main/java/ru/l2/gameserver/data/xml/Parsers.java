package ru.l2.gameserver.data.xml;

import ru.l2.gameserver.data.cache.HtmCache;
import ru.l2.gameserver.data.xml.holder.BuyListHolder;
import ru.l2.gameserver.data.xml.holder.MultiSellHolder;
import ru.l2.gameserver.data.xml.parser.*;
import ru.l2.gameserver.manager.ReflectionManager;
import ru.l2.gameserver.tables.SkillTable;

public abstract class Parsers {
    public static void parseAll() {
        HtmCache.getInstance().reload();
        StringParser.getInstance().load();
        EnchantSkillParser.getInstance().load();
        SkillTable.getInstance().load();
        OptionDataParser.getInstance().load();
        ItemTemplateParser.getInstance().load();
        FStringParser.getInstance().load();
        VariationGroupParser.getInstance().load();
        VariationChanceParser.getInstance().load();
        BaseStatsBonusParser.getInstance().load();
        LevelBonusParser.getInstance().load();
        NpcTemplateParser.getInstance().load();
        MoveRouteParser.getInstance().load();
        HitCondBonusParser.getInstance().load();
        DomainParser.getInstance().load();
        RestartPointParser.getInstance().load();
        StaticObjectParser.getInstance().load();
        DoorParser.getInstance().load();
        ZoneParser.getInstance().load();
        SpawnParser.getInstance().load();
        InstantZoneParser.getInstance().load();
        ReflectionManager.getInstance().init();
        SkillAcquireParser.getInstance().load();
        ResidenceParser.getInstance().load();
        EventParser.getInstance().load();
        CubicParser.getInstance().load();
        RecipeParser.getInstance().load();
        BuyListHolder.getInstance();
        MultiSellHolder.getInstance();
        HennaParser.getInstance().load();
        EnchantItemParser.getInstance().load();
        SoulCrystalParser.getInstance().load();
        ArmorSetsParser.getInstance().load();
        FishDataParser.getInstance().load();
        CapsuleItemParser.getInstance().load();
        //Custom content
        PolymorphedParser.getInstance().load();
        ShadowTradeParser.getInstance().load();
        //CustomEventsArenas parsers
        CtFArenaParser.getInstance().load();
        KoreanStyleArenaParser.getInstance().load();
        LastHeroArenaParser.getInstance().load();
        TvTArenaParser.getInstance().load();
        EventEquipParser.getInstance().load();
    }
}
