/*
 * Referee.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.referees;

import ru.robotics.actors.Actor;

/**
 * Referee
 */
public interface Referee {

  /**
   * Get winner of fight
   * @return winner actor {@link Actor}
   */
  Actor getWinner();

}
