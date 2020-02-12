/*
 * AbstractEquipped.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.equipment.equipped;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import ru.robotics.equipment.Equipment;

/**
 * Equipped implementation with all necessary functional.
 * Thread safe
 * @param <E> concrete type of equipment
 */
public class EquippedImpl<E extends Equipment> implements Equipped<E> {

  /**
   * Set of equipments
   */
  private final Set<E> equipments = new HashSet<>();
  /**
   * Set of used equipments
   */
  private final Set<E> usedEquipments = new CopyOnWriteArraySet<>();

  /**
   * Constructor
   * @param equipments collection of equipments
   */
  public EquippedImpl(Collection<E> equipments) {
    this.equipments.addAll(Objects.requireNonNullElse(equipments, Collections.emptyList()));
    setEquipments();
  }

  /**
   * set equipments
   */
  @Override
  public void setEquipments() {
    usedEquipments.addAll(equipments);
  }

  /**
   * unset equipments
   */
  @Override
  public void unsetEquipments() {
    usedEquipments.clear();
  }

  /**
   * Get equipments
   * @return list of equipments
   */
  @Override
  public List<E> getEquipments() {
    return new ArrayList<>(usedEquipments);
  }

}