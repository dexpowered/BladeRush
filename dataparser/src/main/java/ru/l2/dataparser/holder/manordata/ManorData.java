package ru.l2.dataparser.holder.manordata;

import ru.l2.dataparser.annotations.array.ObjectArray;
import ru.l2.dataparser.annotations.value.IntValue;
import ru.l2.dataparser.annotations.value.StringValue;

/**
 * @author : Camelion, KilRoy
 * @date : 30.08.12 13:14
 */
public class ManorData {
    @IntValue
    private int manor_id;
    @IntValue
    private int residence_id;
    @StringValue
    private String manor_name;
    @ObjectArray
    private CropInfo[] crop_list;
    @ObjectArray
    private ProcureInfo[] procure_list;

    public int getManorId() {
        return manor_id;
    }

    public int getResidenceId() {
        return residence_id;
    }

    public String getManorName() {
        return manor_name;
    }

    public CropInfo[] getCropList() {
        return crop_list;
    }

    public ProcureInfo[] getProcureList() {
        return procure_list;
    }
}