/*
 * Observable.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.actors;

import java.util.List;
import ru.robotics.referees.Observer;

/**
 * Object which observes by observer
 * @param <T> concrete type of observable object
 */
public interface Observable<T extends Observable> {

  /**
   * Add observer
   * @param observer observer for adding
   */
  void addObserver(Observer<T> observer);

  /**
   * Remove observer
   * @param observer observer for removing
   */
  void removeObserver(Observer<T> observer);

  /**
   * Get list of observers
   * @return list of observers
   */
  List<Observer<T>> getObservers();

}
