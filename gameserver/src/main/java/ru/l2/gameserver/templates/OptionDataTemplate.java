package ru.l2.gameserver.templates;

import ru.l2.gameserver.model.Skill;
import ru.l2.gameserver.stats.StatTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptionDataTemplate extends StatTemplate {
    private List<Skill> _skills = Collections.emptyList();
    private final int _id;

    public OptionDataTemplate(final int id) {
        _id = id;
    }

    public void addSkill(final Skill skill) {
        if(_skills.isEmpty()) {
            _skills = new ArrayList<>();
        }
        _skills.add(skill);
    }

    public List<Skill> getSkills() {
        return _skills;
    }

    public int getId() {
        return _id;
    }
}
