package com.zjut.ida.service;

import com.zjut.ida.entity.Achievement;
import com.zjut.ida.entity.Scholar;

/**
 * @author Veloma on 2020/10/21.
 */

public interface AchievementService {
    Achievement findAchievementByName(String name1, String name2);
    Scholar findScholarByScholarId(Long id);
}
