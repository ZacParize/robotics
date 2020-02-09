/*
 * Killer.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.actors;

import java.util.Collection;
import java.util.List;
import ru.robotics.actions.Action;
import ru.robotics.referees.Observer;
import ru.robotics.weapons.Weapon;

/**
 * Observable actor implementation.
 * Class implements composition of {@link Actor} and {@link Observable}
 * @see Actor
 * @see Observable
 */
public class Killer implements Actor, Observable<Killer> {

  /**
   * Constant of life level
   */
  private static final int LIFE = 100;
  /**
   * Observable delegate
   */
  private final Observable<Killer> observable = new ObservableImpl<>();
  /**
   * Actor delegate
   */
  private final Actor actor;

  /**
   * Constructor for actor creation
   * @param id identity of actor
   * @param weapons collection of weapons
   */
  public Killer(String id, Collection<Weapon> weapons) {
    this.actor = new ActorImpl(id, LIFE, weapons);
  }

  /**
   * Add observer.
   * All functionality takes from to observable delegate
   * @param observer observer for adding
   */
  @Override
  public void addObserver(Observer<Killer> observer) {
    observable.addObserver(observer);
  }

  /**
   * Remove observer.
   * All functionality takes from observable delegate
   * @param observer observer for removing
   */
  @Override
  public void removeObserver(Observer<Killer> observer) {
    observable.removeObserver(observer);
  }

  /**
   * Get list of observers.
   * All functionality takes from observable delegate
   * @return list of observers
   */
  @Override
  public List<Observer<Killer>> getObservers() {
    return observable.getObservers();
  }

  /**
   * Get actor's id.
   * All functionality takes from actor delegate
   * @return actor's id
   */
  @Override
  public String getId() {
    return actor.getId();
  }

  /**
   * Attack passed opponent and notify observer
   * All functionality takes from actor and observable delegates
   * @param opponent competitor
   */
  @Override
  public void attack(Actor opponent) {
    actor.attack(opponent);
    observable.getObservers().forEach(o -> o.notifyAboutAction(this, Action.ATTACK));
  }

  /**
   * Protect yourself and notify observer
   * All functionality takes from actor and observable delegates
   * @param damage amount of damage
   */
  @Override
  public void protect(int damage) {
    if (!actor.isAlive()) {
      return;
    }
    actor.protect(damage);
    observable.getObservers().forEach(o -> o.notifyAboutAction(this, Action.PROTECT));
  }

  /**
   * Check if actor is alive
   * All functionality takes from actor delegate
   * @return {@code true} if actor is still alive
   */
  @Override
  public boolean isAlive() {
    return actor.isAlive();
  }

}
