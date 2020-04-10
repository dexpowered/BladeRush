package ru.l2.dataparser.holder.cubicdata;

import ru.l2.dataparser.annotations.class_annotations.ParseSuper;

/**
 * @author : Camelion
 * @date : 26.08.12 13:12
 */
@ParseSuper
public class Cubic extends DefaultCubicData {
    @Override
    public boolean isCubic() {
        return true;
    }
}
