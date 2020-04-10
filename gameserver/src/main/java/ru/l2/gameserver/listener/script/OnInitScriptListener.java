package ru.l2.gameserver.listener.script;


import ru.l2.gameserver.listener.ScriptListener;

/**
 * @author VISTALL
 * @date 1:06/19.08.2011
 */
@FunctionalInterface
public interface OnInitScriptListener extends ScriptListener {
    void onInit();
}
