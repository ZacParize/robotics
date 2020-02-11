/*
 * AbstractActor.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import ru.robotics.referees.Observer;

/**
 * ObservableImpl implementation with all necessary functional.
 * Thread safe
 * @param <T> concrete type of observable
 * @see Observable
 */
public class ObservableImpl<T extends Observable> implements Observable<T> {

  /**
   * Set of observers
   */
  private final Set<Observer<T>> observers = ConcurrentHashMap.newKeySet();

  /**
   * Constructor
   */
  public ObservableImpl() {
  }

  /**
   * Add observer
   * @param observer observer for adding
   */
  @Override
  public void addObserver(Observer<T> observer) {
    if (observer == null) {
      return;
    }
    observers.add(observer);
  }

  /**
   * Remove observer
   * @param observer observer for removing
   */
  @Override
  public void removeObserver(Observer<T> observer) {
    if (observer == null) {
      return;
    }
    observers.remove(observer);
  }

  /**
   * Get list of observers
   * @return list of observers
   */
  @Override
  public List<Observer<T>> getObservers() {
    return new ArrayList<>(observers);
  }

}
