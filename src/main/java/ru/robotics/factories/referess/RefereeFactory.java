/*
 * RefereeFactory.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.factories.referess;

import java.util.Collection;
import ru.robotics.actors.Actor;
import ru.robotics.actors.Observable;
import ru.robotics.referees.SimpleReferee;

/**
 * Kind of referee factory.
 * Basically it's not an abstract factory because create extra interfaces in
 * current situation is overhead
 */
public class RefereeFactory {

  private RefereeFactory() {
  }

  /**
   * SimpleReferee {@link SimpleReferee} creator
   * @param actors collection of actors
   * @param <T> concrete type of actor {@link Actor} and observable {@link Observable}
   * @return simple referee instance
   */
  public static <T extends Actor & Observable<T>> SimpleReferee<T> createSimpleReferee(Collection<T> actors) {
    return new SimpleReferee<>(actors);
  }

}
