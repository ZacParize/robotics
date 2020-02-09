/*
 * ActorImpl.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.actors;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import ru.robotics.weapons.Weapon;

/**
 * Actor implementation with all necessary functional.
 * Thread safe
 * @see Actor
 */
public class ActorImpl implements Actor {

  /**
   * Actor's id
   */
  private final String id;
  /**
   * Set of actor's weapons
   */
  private final Set<Weapon> weapons = new HashSet<>();
  /**
   * Life level of actor.
   * Actor could be shared between few threads so that's why life level stores
   * in {@link AtomicInteger}
   */
  private final AtomicInteger life;

  /**
   * Constructor for actor creation
   * @param id identity of actor
   * @param life base life level
   * @param weapons collection of weapons
   */
  public ActorImpl(String id, int life, Collection<Weapon> weapons) {
    this.id = id;
    this.weapons.addAll(Objects.requireNonNullElse(weapons, Collections.emptyList()));
    this.life = new AtomicInteger(life);
  }

  /**
   * Get actor's id
   * @return actor's id
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * Attack passed opponent
   * @param opponent competitor
   */
  @Override
  public void attack(Actor opponent) {
    if (opponent == null) {
      return;
    }
    // count damage from all weapons
    int damage = weapons.stream().map(Weapon::damage).reduce(0, (a, b) -> a + b);
    opponent.protect(damage);
    System.out.println(id + " have just attacked " + opponent.getId() + " with damage " + damage);
  }

  /**
   * Protect yourself
   * @param damage amount of damage
   */
  @Override
  public void protect(int damage) {
    // happens before - actual value
    if (!isAlive()) {
      return;
    }
    // CAS for count current life level
    life.addAndGet(-Math.abs(damage));
  }

  /**
   * Check if actor is alive
   * @return {@code true} if actor is still alive
   */
  @Override
  public boolean isAlive() {
    return life.get() > 0;
  }
}
