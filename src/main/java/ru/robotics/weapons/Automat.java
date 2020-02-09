/*
 * Automat.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.weapons;

import java.util.Collection;
import ru.robotics.equipment.AttackEquipment;
import ru.robotics.equipment.equipped.EquippedImpl;

/**
 * Automat implementation of weapon
 * @param <E> concrete type of attack equipment
 * @see EquippedImpl
 * @see Weapon
 */
public class Automat<E extends AttackEquipment> extends EquippedImpl<E> implements Weapon {

  private static final int DAMAGE = 10;
  private static final double ACCURACY = 1.5;

  /**
   * Constructor
   * @param equipments collection of equipments
   */
  public Automat(Collection<E> equipments) {
    super(equipments);
  }

  /**
   * Get damage
   * @return damage effect
   */
  @Override
  public int damage() {
    return getEquipments().stream().map(E::get)
        .reduce(DAMAGE, (a, b) -> a + (int) Math.round(b * ACCURACY));
  }

}
