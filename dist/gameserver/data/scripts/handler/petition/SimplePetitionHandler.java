package handler.petition;

import ru.l2.gameserver.handler.petition.IPetitionHandler;
import ru.l2.gameserver.model.Player;

public class SimplePetitionHandler implements IPetitionHandler {
    @Override
    public void handle(final Player player, final int id, final String txt) {
        player.sendMessage(txt);
    }
}
