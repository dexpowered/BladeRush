package services;

import ru.l2.gameserver.Announcements;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import ru.l2.gameserver.listener.script.OnInitScriptListener;
import ru.l2.gameserver.model.Player;
import ru.l2.gameserver.model.actor.listener.PlayerListenerList;
import ru.l2.gameserver.model.entity.olympiad.OlympiadSystemManager;

public class OlyInform implements OnInitScriptListener {
    private static final long SECONDS_IN_MINUTE = 60L;
    private static final long SECONDS_IN_HOUR = 3600L;
    private static final long SECONDS_IN_A_DAY = 86400L;

    public static void informOlyEnd(final Player player) {
        final long now = System.currentTimeMillis();
        final long olyEndTime = OlympiadSystemManager.getInstance().getSeasonEndTime();
        if (now / 1000L > olyEndTime) {
            return;
        }
        long reamainingSec = olyEndTime - now / 1000L;
        final int remainingDays = (int) (reamainingSec / 86400L);
        reamainingSec %= 86400L;
        final int remainingHours = (int) (reamainingSec / 3600L);
        reamainingSec %= 3600L;
        final int remainingMinutes = (int) (reamainingSec / 60L);
        Announcements.getInstance().announceToPlayerByCustomMessage(player, "l2p.gameserver.model.entity.OlympiadGame.EndSeasonTime", new String[]{String.valueOf(remainingDays), String.valueOf(remainingHours), String.valueOf(remainingMinutes)});
    }

    @Override
    public void onInit() {
        if (Config.ANNOUNCE_OLYMPIAD_GAME_END) {
            PlayerListenerList.addGlobal((OnPlayerEnterListener) OlyInform::informOlyEnd);
        }
    }
}
