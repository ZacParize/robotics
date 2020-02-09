/*
 * Equipped.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.equipment.equipped;

import java.util.List;
import ru.robotics.equipment.Equipment;

/**
 * Any kind of equipped
 * @see Equipment
 */
public interface Equipped<T extends Equipment> {

  /**
   * set equipments
   */
  void setEquipments();

  /**
   * unset equipments
   */
  void unsetEquipments();

  /**
   * Get equipments
   * @return list of equipments
   */
  List<T> getEquipments();

}
