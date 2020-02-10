/*
 * EquippedKiller.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.actors;

import java.util.Collection;
import java.util.List;
import ru.robotics.equipment.ProtectEquipment;
import ru.robotics.equipment.equipped.Equipped;
import ru.robotics.equipment.equipped.EquippedImpl;
import ru.robotics.weapons.Weapon;

/**
 * Protect equipped killer implementation
 * Decorator of killer {@link Killer}
 * @see Killer
 * @see ProtectEquipment
 */
public class ProtectEquippedKiller extends Killer implements Equipped<ProtectEquipment> {

  /**
   * Protect equipped delegate
   */
  private final Equipped<ProtectEquipment> equippedDelegate;

  /**
   * Constructor
   * @param id identity of actor
   * @param weapons collection of weapons
   * @param protectEquipments protect equipments
   */
  public ProtectEquippedKiller(String id, Collection<Weapon> weapons, Collection<ProtectEquipment> protectEquipments) {
    super(id, weapons);
    this.equippedDelegate = new EquippedImpl<>(protectEquipments);
  }

  /**
   * Set equipments.
   * All functionality takes from equipped delegate
   */
  @Override
  public void setEquipments() {
    equippedDelegate.setEquipments();
  }

  /**
   * Unset equipments.
   * All functionality takes from equipped delegate
   */
  @Override
  public void unsetEquipments() {
    equippedDelegate.unsetEquipments();
  }

  /**
   * Get list of equipments.
   * All functionality takes from equipped delegate
   * @return list of equipments
   */
  @Override
  public List<ProtectEquipment> getEquipments() {
    return equippedDelegate.getEquipments();
  }

  /**
   * Protect yourself by equipment and call killer's {@link Killer#protect}
   * @param damage amount of damage
   */
  @Override
  public void protect(int damage) {
    int tempDamage = Math.abs(damage) - Math.abs(equippedDelegate.getEquipments().stream().map(ProtectEquipment::get)
        .reduce(0, (a, b) -> a + b));
    super.protect(tempDamage <= 0 ? 0 : tempDamage);
  }
}
