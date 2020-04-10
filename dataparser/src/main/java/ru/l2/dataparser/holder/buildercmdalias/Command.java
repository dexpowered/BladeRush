package ru.l2.dataparser.holder.buildercmdalias;

import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author : Camelion
 * @date : 25.08.12 22:50
 */
public class Command {
    @StringValue
    public String command;
    @StringValue
    public String alias;
}
