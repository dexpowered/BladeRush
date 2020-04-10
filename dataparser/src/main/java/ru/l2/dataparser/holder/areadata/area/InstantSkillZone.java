package ru.l2.dataparser.holder.areadata.area;

import ru.l2.dataparser.annotations.class_annotations.ParseSuper;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author : Camelion
 * @date : 25.08.12  13:59
 */
@ParseSuper
public class InstantSkillZone extends DefaultArea {
    @StringValue
    private String skill_name; // название скила, используемого зоной

    @IntValue
    private int skill_prob; // неизвестно

    public InstantSkillZone(DefaultArea defaultSetting) {
        super(defaultSetting);
        skill_name = ((InstantSkillZone) defaultSetting).skill_name;
        skill_prob = ((InstantSkillZone) defaultSetting).skill_prob;
    }

    public InstantSkillZone() {


    }
}
