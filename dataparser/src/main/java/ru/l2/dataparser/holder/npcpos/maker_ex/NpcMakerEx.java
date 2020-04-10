package ru.l2.dataparser.holder.npcpos.maker_ex;

import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.ObjectValue;
import ru.l2.dataparser.annotations.value.StringValue;
import ru.l2.dataparser.holder.NpcPosHolder;
import ru.l2.dataparser.holder.npcpos.DefaultMaker;
import ru.l2.dataparser.holder.npcpos.common.AIParameters;

import java.util.List;

/**
 * @author : Camelion
 * @date : 30.08.12  21:15
 */
@ParseSuper
public class NpcMakerEx extends DefaultMaker {
    @StringValue
    private String ai;
    @ObjectValue(dotAll = false, objectFactory = NpcPosHolder.AiParamsObjectFactory.class)
    private final AIParameters ai_parameters = new AIParameters(); // Присутствует всегда, params может быть пустым
    @Element(start = "npc_ex_begin", end = "npc_ex_end")
    private List<NpcEx> npcs;

    public String getAI() {
        return ai;
    }

    public AIParameters getAIParameters() {
        return ai_parameters;
    }

    public List<NpcEx> getNpcs() {
        return npcs;
    }
}