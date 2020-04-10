package handler.items;

import ru.l2.gameserver.data.xml.holder.ItemTemplateHolder;
import ru.l2.gameserver.handler.items.IItemHandler;
import ru.l2.gameserver.handler.items.ItemHandler;
import ru.l2.gameserver.listener.script.OnInitScriptListener;
import ru.l2.gameserver.model.Playable;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.items.ItemInstance;
import ru.l2.gameserver.utils.Location;
import ru.l2.gameserver.utils.Log;
import ru.l2.gameserver.utils.Log.ItemLog;

import java.util.Objects;
import java.util.stream.IntStream;

public abstract class ScriptItemHandler implements OnInitScriptListener, IItemHandler {
    @Override
    public void dropItem(final Player player, ItemInstance item, final long count, final Location loc) {
        if (item.isEquipped()) {
            player.getInventory().unEquipItem(item);
            player.sendUserInfo(true);
        }
        item = player.getInventory().removeItemByObjectId(item.getObjectId(), count);
        if (item == null) {
            player.sendActionFailed();
            return;
        }
        Log.LogItem(player, ItemLog.Drop, item);
        item.dropToTheGround(player, loc);
        player.disableDrop(1000);
        player.sendChanges();
    }

    @Override
    public boolean pickupItem(final Playable playable, final ItemInstance item) {
        return true;
    }

    protected boolean isShotsHandler() {
        return false;
    }

    @Override
    public void onInit() {
        ItemHandler.getInstance().registerItemHandler(this);
        if (isShotsHandler()) {
            IntStream.of(getItemIds()).mapToObj(itemId -> ItemTemplateHolder.getInstance().getTemplate(itemId)).filter(Objects::nonNull).forEach(itemTemplate -> itemTemplate.setIsShotItem(true));
        }
    }
}
