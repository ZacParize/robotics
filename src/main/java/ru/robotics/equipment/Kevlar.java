/*
 * Kevlar.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.equipment;

/**
 * Kevlar implementation of {@link ProtectEquipment}
 */
public class Kevlar implements ProtectEquipment {

  /**
   * Get equipment effect
   * @return equipment effect
   */
  @Override
  public Integer get() {
    return 5;
  }

}
