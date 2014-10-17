package org.codecranachan.asteroidpush.base.scenario;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.base.ui.simulation.Viewport;

public class Referee {
   private Collection<Rule> rules;
   private Map<Player, Role> participants;

   public Referee() {
      rules = new LinkedList<Rule>();
      participants = new HashMap<Player, Role>();
   }

   public void clear() {
      rules.clear();
   }

   public void addParticipant(Player player, Role role) {
      participants.put(player, role);
   }

   public void addRule(Rule rule) {
      rules.add(rule);
   }

   public Viewport getInterfaceFor(Player player) {
      Role role = getRoleFor(player);
      return role.getInterface();
   }

   private Role getRoleFor(Player player) {
      if (participants.containsKey(player)) {
         return participants.get(player);
      }
      return new NullRole(player);
   }

   public void update(Simulation simulation, int frame) {
      for (Rule item : rules) {
         item.update(simulation, frame);
      }
   }
}
