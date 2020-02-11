/*
 * Referee.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.referees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.robotics.actions.Action;
import ru.robotics.actors.Actor;
import ru.robotics.actors.Observable;
import ru.robotics.referees.strategies.SimpleFightStrategy;

/**
 * Observer referee implementation
 *
 * @param <T> concrete type of actor
 * @see Actor
 * @see Observable
 * @see Referee
 * @see Observer
 */
public class SimpleReferee<T extends Actor & Observable<T>> implements Observer<T>, Referee {

  /**
   * Map of participants
   */
  private final Map<String, T> actors = new HashMap<>();
  /**
   * Map of actors activities
   */
  private final Map<String, Action> actorAction = new ConcurrentHashMap<>();
  /**
   * Inner thread pool for fights execution
   */
  private final ExecutorService threadPool = Executors.newCachedThreadPool();

  /**
   * Constructor
   *
   * @param actors collection of actors
   */
  public SimpleReferee(Collection<T> actors) {
    Optional.ofNullable(actors).ifPresent(actorsCollection ->
        actorsCollection.forEach(actor -> {
          actor.addObserver(this);
          this.actors.put(actor.getId(), actor);
          this.actorAction.put(actor.getId(), Action.NOTHING);
        }));
  }

  /**
   * Observe duel process
   */
  @Override
  public void observe() {
    if (actorAction.isEmpty()) {
      return;
    }

    final Collection<Callable<Actor>> tasks = new ArrayList<>();
    final List<T> fightParticipants = new ArrayList<>(2);
    Iterator<Map.Entry<String, Action>> iterator;

    while (actorAction.size() > 1) {
      iterator = actorAction.entrySet().iterator();
      tasks.clear();
      fightParticipants.clear();

      while (iterator.hasNext()) {
        fightParticipants.add(actors.get(iterator.next().getKey()));
        if (fightParticipants.size() == 2) {
          tasks.add(new SimpleFightStrategy(fightParticipants.get(0), fightParticipants.get(1),
              actorAction));
          fightParticipants.clear();
        }
      }

      try {
        threadPool.invokeAll(tasks);
      } catch (Exception e) {
        Thread.currentThread().interrupt();
        System.out
            .println("Something interrupted referee: " + e.getLocalizedMessage() + ". Game over");
        throw new IllegalStateException(e);
      }

      actors.entrySet().stream().filter(e -> !e.getValue().isAlive())
          .forEach(e -> actorAction.remove(e.getKey()));
    }

    if (!threadPool.isShutdown()) {
      threadPool.shutdownNow();
    }
  }

  /**
   * Notify observer about actor's action
   *
   * @param actor actor who notifies
   * @param action actor notification action
   */
  @Override
  public void notifyAboutAction(T actor, Action action) {
    if (actor == null || action == null) {
      return;
    }
    actorAction.put(actor.getId(), action);
  }

  /**
   * Get winner of duel
   *
   * @return winner actor
   */
  @Override
  public Actor getWinner() {
    if (actors.isEmpty()) {
      return null;
    }
    if (actors.size() > 1) {
      observe();
    }
    T winner = actors.get(actorAction.keySet().iterator().next());
    System.out.println("The winner is " + winner.getId());
    return winner;
  }

}
