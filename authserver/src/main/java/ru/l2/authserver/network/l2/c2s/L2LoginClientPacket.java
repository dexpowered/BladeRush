package ru.l2.authserver.network.l2.c2s;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.l2.authserver.network.l2.L2LoginClient;
import ru.l2.commons.net.nio.impl.ReceivablePacket;

public abstract class L2LoginClientPacket extends ReceivablePacket<L2LoginClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(L2LoginClientPacket.class);

    @Override
    protected final boolean read() {
        try {
            readImpl();
            return true;
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    @Override
    public void run() {
        try {
            runImpl();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    protected abstract void readImpl();

    protected abstract void runImpl() throws Exception;
}
