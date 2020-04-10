package ru.l2.dataparser.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.holder.formationinfo.FormationInfo;

/**
 * @author : Camelion
 * @date : 27.08.12 13:04
 */
public class FormationInfoHolder extends AbstractHolder {
    private static final FormationInfoHolder ourInstance = new FormationInfoHolder();
    @Element(start = "formation_begin", end = "formation_end")
    private FormationInfo formationInfo;

    private FormationInfoHolder() {
    }

    public static FormationInfoHolder getInstance() {
        return ourInstance;
    }

    @Override
    public int size() {
        return 1;
    }

    public FormationInfo getFormationInfo() {
        return formationInfo;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}