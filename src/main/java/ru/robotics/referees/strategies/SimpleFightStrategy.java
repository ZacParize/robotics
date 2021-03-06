/*
 * FightImpl.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.referees.strategies;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Callable;
import ru.robotics.actions.Action;
import ru.robotics.actors.Actor;

/**
 * Base simple fight strategy
 * @see Callable
 * @see Actor
 */
public class SimpleFightStrategy implements Callable<Optional<Actor>> {

  private final Actor actor1;
  private final Actor actor2;
  private final Map<String, Action> actions;

  public SimpleFightStrategy(Actor actor1, Actor actor2, Map<String, Action> actions) {
    this.actor1 = actor1;
    this.actor2 = actor2;
    this.actions = actions;
  }

  /**
   * The simplest fight strategy "the strongest will win"
   * @return nothing
   * @throws Exception
   */
  @Override
  public Optional<Actor> call() throws Exception {
    if (actor1 == null || actor2 == null || actions == null) {
      return Optional.empty();
    }
    actions.put(actor1.getId(), new Random().nextInt(2) == 0 ? Action.ATTACK : Action.PROTECT);
    actions.put(actor2.getId(),
        actions.get(actor1.getId()) == Action.ATTACK ? Action.PROTECT : Action.ATTACK);
    while (actor1.isAlive() && actor2.isAlive()) {
      if (actions.get(actor1.getId()) == Action.PROTECT) {
        actor1.attack(actor2);
      } else {
        actor2.attack(actor1);
      }
    }
    return Optional.of(actor1.isAlive() ? actor1 : actor2);
  }
}
