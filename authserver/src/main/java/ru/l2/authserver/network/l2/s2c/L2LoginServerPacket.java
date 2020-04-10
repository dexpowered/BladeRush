package ru.l2.authserver.network.l2.s2c;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.l2.authserver.network.l2.L2LoginClient;
import ru.l2.commons.net.nio.impl.SendablePacket;

public abstract class L2LoginServerPacket extends SendablePacket<L2LoginClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(L2LoginServerPacket.class);

    @Override
    public final boolean write() {
        try {
            writeImpl();
            return true;
        } catch (Exception e) {
            LOGGER.error("Client: " + getClient() + " - Failed writing: " + getClass().getSimpleName() + "!", e);
            return false;
        }
    }

    protected abstract void writeImpl();
}
