/*
 * Grenade.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.equipment;

/**
 * Grenade attack equipment implementation of {@link AttackEquipment}
 * @see Equipment
 */
public class Grenade implements AttackEquipment {

  /**
   * Get equipment effect
   * @return equipment effect
   */
  @Override
  public Integer get() {
    return 15;
  }

}
