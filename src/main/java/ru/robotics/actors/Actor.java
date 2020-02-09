/*
 * Actor.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.actors;

/**
 * Interface represents actor itself.
 * All participants should implement the interface
 */
public interface Actor {

  /**
   * Get actor's id
   * @return actor's id
   */
  String getId();

  /**
   * Attack passed opponent
   * @param opponent competitor
   */
  void attack(Actor opponent);

  /**
   * Protect yourself
   * @param damage amount of damage
   */
  void protect(int damage);

  /**
   * Check if actor is alive
   * @return {@code true} if actor is still alive
   */
  boolean isAlive();

}
