/*
 * Observer.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.referees;

import ru.robotics.actions.Action;
import ru.robotics.actors.Observable;

/**
 * Observer
 * @param <T> concrete type of observable
 * @see Observable
 */
public interface Observer<T extends Observable> {

  /**
   * Observe method
   */
  void observe();

  /**
   * Notify observer about actor's action
   * @param actor actor who notifies
   * @param action actor notification action
   */
  void notifyAboutAction(T actor, Action action);

}
