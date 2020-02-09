/*
 * App.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics;

import java.util.List;
import ru.robotics.equipment.Kevlar;
import ru.robotics.equipment.SpecialBullet;
import ru.robotics.factories.actors.ActorFactory;
import ru.robotics.factories.referess.RefereeFactory;
import ru.robotics.weapons.Gun;

public class App {

  public static void main(String[] args) {
    RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller("killer1", List.of(new Gun<>(List.of(new SpecialBullet())))),
        ActorFactory.createProtectEquippedKiller("killer2", List.of(new Gun<>(List.of())), List.of(new Kevlar())))
    ).getWinner();
  }

}
