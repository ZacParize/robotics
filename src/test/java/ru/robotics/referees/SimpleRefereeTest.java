/*
 * SimpleRefereeTest.java
 *
 * Copyright 2017-2020 BCS-Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of BCS-Technologies.
 * Use is subject to license terms.
 */

package ru.robotics.referees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.robotics.equipment.Grenade;
import ru.robotics.equipment.Kevlar;
import ru.robotics.equipment.SpecialBullet;
import ru.robotics.factories.actors.ActorFactory;
import ru.robotics.factories.referess.RefereeFactory;
import ru.robotics.weapons.Automat;
import ru.robotics.weapons.Gun;

class SimpleRefereeTest {

  @Test
  void testNulls() {
    Referee referee = RefereeFactory.createSimpleReferee(null);
    assertFalse(referee.getWinner().isPresent());

    final String id1 = "killer1";
    final String id2 = "killer2";

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, null),
        ActorFactory.createKiller(id2, Collections.singleton(new Gun<>(null)))
    ));
    assertEquals(id2, referee.getWinner().get().getId());

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, null),
        ActorFactory.createProtectEquippedKiller(id2, Collections.singleton(new Automat<>(null)), null)
    ));
    assertEquals(id2, referee.getWinner().get().getId());

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, Collections.singleton(new Automat<>(null))),
        ActorFactory.createProtectEquippedKiller(id2, null, null)
    ));
    assertEquals(id1, referee.getWinner().get().getId());
  }

  @Test
  void testOneParticipant() {
    final String id1 = "killer1";

    Referee referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, null)
    ));
    assertEquals(id1, referee.getWinner().get().getId());
  }

  @Test
  void testTwoParticipants() {
    final String id1 = "killer1";
    final String id2 = "killer2";

    Referee referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, Collections.singleton(new Gun<>(Collections.emptyList()))),
        ActorFactory.createProtectEquippedKiller(id2,
            Collections.singleton(new Gun<>(Collections.emptyList())),
            Collections.singleton(new Kevlar())))
    );
    assertEquals(id2, referee.getWinner().get().getId());

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1,
            Collections.singleton(new Gun<>(Collections.singleton(new SpecialBullet())))),
        ActorFactory.createKiller(id2, Collections.singleton(new Gun<>(Collections.emptyList()))))
    );
    assertEquals(id1, referee.getWinner().get().getId());

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, Collections.singleton(new Gun<>(Collections.emptyList()))),
        ActorFactory
            .createKiller(id2, Collections.singleton(new Automat<>(Collections.emptyList()))))
    );
    assertEquals(id2, referee.getWinner().get().getId());

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory
            .createKiller(id1, Collections.singleton(new Automat<>(Collections.emptyList()))),
        ActorFactory.createKiller(id2,
            Collections.singleton(new Automat<>(Collections.singleton(new SpecialBullet())))))
    );
    assertEquals(id2, referee.getWinner().get().getId());
  }

  @Test
  void testThreeParticipants() {
    final String id1 = "killer1";
    final String id2 = "killer2";
    final String id3 = "killer3";

    Referee referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, Collections.singleton(new Gun<>(Collections.emptyList()))),
        ActorFactory.createKiller(id2,
            Collections.singleton(new Gun<>(Collections.singleton(new SpecialBullet())))),
        ActorFactory
            .createProtectEquippedKiller(id3, Collections.singleton(new Automat<>(Collections.emptyList())), Collections.singleton(new Kevlar())))
    );
    assertEquals(id3, referee.getWinner().get().getId());

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, Collections.singleton(new Gun<>(Collections.emptyList()))),
        ActorFactory.createKiller(id2,
            Collections.singleton(new Gun<>(Collections.singleton(new SpecialBullet())))),
        ActorFactory
            .createKiller(id3, Collections.singleton(new Automat<>(Collections.singleton(new SpecialBullet())))))
    );
    assertEquals(id3, referee.getWinner().get().getId());

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, Collections.singleton(new Gun<>(Collections.emptyList()))),
        ActorFactory.createKiller(id2,
            Collections.singleton(new Gun<>(Collections.singleton(new SpecialBullet())))),
        ActorFactory
            .createKiller(id3, Collections.singleton(new Automat<>(Collections.singleton(new SpecialBullet())))))
    );
    assertEquals(id3, referee.getWinner().get().getId());

    referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, Collections.singleton(new Gun<>(Collections.emptyList()))),
        ActorFactory.createKiller(id2,
            Collections.singleton(new Automat<>(List.of(new SpecialBullet(), new Grenade())))),
        ActorFactory
            .createProtectEquippedKiller(id3, Collections.singleton(new Automat<>(Collections.emptyList())), Collections.singleton(new Kevlar())))
    );
    assertEquals(id2, referee.getWinner().get().getId());
  }

  @Test
  void testFourParticipants() {
    final String id1 = "killer1";
    final String id2 = "killer2";
    final String id3 = "killer3";
    final String id4 = "killer4";

    Referee referee = RefereeFactory.createSimpleReferee(List.of(
        ActorFactory.createKiller(id1, Collections.singleton(new Automat<>(Collections.emptyList()))),
        ActorFactory.createKiller(id2,
            Collections.singleton(new Automat<>(List.of(new SpecialBullet())))),
        ActorFactory
            .createProtectEquippedKiller(id3, Collections.singleton(new Automat<>(List.of(new SpecialBullet()))), Collections.singleton(new Kevlar())),
        ActorFactory
            .createProtectEquippedKiller(id4, Collections.singleton(new Automat<>(List.of(new SpecialBullet(), new Grenade()))), Collections.singleton(new Kevlar())))
    );
    assertEquals(id4, referee.getWinner().get().getId());
  }

}
