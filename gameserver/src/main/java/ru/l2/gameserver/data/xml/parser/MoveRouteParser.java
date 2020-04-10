package ru.l2.gameserver.data.xml.parser;

import org.jdom2.Element;
import ru.l2.commons.data.xml.AbstractDirParser;
import ru.l2.gameserver.Config;
import ru.l2.gameserver.data.xml.holder.MoveRouteHolder;
import ru.l2.gameserver.network.lineage2.components.ChatType;
import ru.l2.gameserver.templates.moveroute.MoveNode;
import ru.l2.gameserver.templates.moveroute.MoveRoute;
import ru.l2.gameserver.templates.moveroute.MoveRouteType;

import java.io.File;

public class MoveRouteParser extends AbstractDirParser<MoveRouteHolder> {

    private MoveRouteParser() {
        super(MoveRouteHolder.getInstance());
    }

    public static MoveRouteParser getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public File getXMLDir() {
        return new File(Config.DATAPACK_ROOT, "./data/xml/superpointinfo");
    }

    @Override
    public boolean isIgnored(final File f) {
        return false;
    }

    @Override
    protected void readData(final MoveRouteHolder holder, final Element rootElement) {
        for (final Element e : rootElement.getChildren()) {
            final String name = e.getAttributeValue("name");
            final MoveRouteType type = MoveRouteType.valueOf(e.getAttributeValue("type"));
            final boolean running = Boolean.parseBoolean(e.getAttributeValue("is_running"));
            final MoveRoute moveRoute = new MoveRoute(name, type, running);
            for (final Element nodeElement : e.getChildren()) {
                final int x = Integer.parseInt(nodeElement.getAttributeValue("x"));
                final int y = Integer.parseInt(nodeElement.getAttributeValue("y"));
                final int z = Integer.parseInt(nodeElement.getAttributeValue("z"));
                final int socialId = Integer.parseInt(nodeElement.getAttributeValue("social", "0"));
                final long delay = Long.parseLong(nodeElement.getAttributeValue("delay", "0"));
                final String msgAddr = nodeElement.getAttributeValue("msg_addr");
                ChatType chatType = null;
                if (msgAddr != null) {
                    chatType = ChatType.valueOf(nodeElement.getAttributeValue("chat_type"));
                }
                final MoveNode node = new MoveNode(x, y, z, msgAddr, socialId, delay, chatType);
                moveRoute.getNodes().add(node);
            }
            holder.addRoute(moveRoute);
        }
    }

    private static class LazyHolder {
        protected static final MoveRouteParser INSTANCE = new MoveRouteParser();
    }
}
