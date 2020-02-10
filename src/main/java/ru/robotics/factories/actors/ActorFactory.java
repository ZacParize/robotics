/*
 * ActorFactory.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.factories.actors;

import java.util.Collection;
import ru.robotics.actors.Killer;
import ru.robotics.actors.ProtectEquippedKiller;
import ru.robotics.equipment.ProtectEquipment;
import ru.robotics.weapons.Weapon;

/**
 * Kind of actor factory.
 * Basically it's not an abstract factory because create extra interfaces in
 * current situation is overhead
 */
public class ActorFactory {

  private ActorFactory() {
  }

  /**
   * Killer {@link Killer} creator
   * @param id actor id
   * @param weapons collection of weapons
   * @return killer instance
   */
  public static Killer createKiller(String id, Collection<Weapon> weapons) {
    return new Killer(id, weapons);
  }

  /**
   * ProtectEquippedKiller {@link ProtectEquippedKiller} creator
   * @param id actor id
   * @param weapons collection of weapons
   * @param protectEquipments collection of protect equipments
   * @return protect equipped killer instance
   */
  public static Killer createProtectEquippedKiller(String id,
      Collection<Weapon> weapons,
      Collection<ProtectEquipment> protectEquipments) {
    return new ProtectEquippedKiller(id, weapons, protectEquipments);
  }

}
