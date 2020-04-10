package ru.l2.dataparser.holder;

import ru.l2.commons.data.xml.AbstractHolder;
import ru.l2.dataparser.annotations.Element;
import ru.l2.dataparser.holder.enchantoption.EnchantOption;

import java.util.List;

/**
 * @author : Camelion
 * @date : 27.08.12 2:01
 */
public class EnchantOptionHolder extends AbstractHolder {
    private static final EnchantOptionHolder ourInstance = new EnchantOptionHolder();
    @Element(start = "enchant_option_begin", end = "enchant_option_end")
    public List<EnchantOption> enchantOptions;

    private EnchantOptionHolder() {
    }

    public static EnchantOptionHolder getInstance() {
        return ourInstance;
    }

    @Override
    public int size() {
        return enchantOptions.size();
    }

    public List<EnchantOption> getEnchantOptions() {
        return enchantOptions;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}