package ru.l2.dataparser.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.holder.buildercmdalias.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Camelion
 * @date : 25.08.12 22:47
 */
public class BuilderCmdAliasHolder extends AbstractHolder {
    private static final BuilderCmdAliasHolder ourInstance = new BuilderCmdAliasHolder();
    @Element(start = "command_begin", end = "command_end")
    public List<Command> commands;
    private final List<String> allCommands = new ArrayList<>();

    private BuilderCmdAliasHolder() {
    }

    public static BuilderCmdAliasHolder getInstance() {
        return ourInstance;
    }

    @Override
    public void afterParsing() {
        if (!commands.isEmpty()) {
            for (final Command command : commands) {
                allCommands.add(command.command);
            }
        }
    }

    @Override
    public int size() {
        return commands.size();
    }

    public List<Command> getCommands() {
        return commands;
    }

    public String[] getAllStringCommands() {
        return allCommands.toArray(new String[0]);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}